package mif.ps.psp.FirstAssignment.StrategyRealisation.BookRentServices;

import mif.ps.psp.FirstAssignment.StrategyRealisation.AgeGroupDiscount.AgeGroupDiscount;
import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.Model.BookInformation;
import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.Model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BookRentServicesStrategy {
    List<BookInformation> books;
    List<BookInformation> booksWishList;
    List<BookInformation> pickedBooks;
    BookInformation bookInfo;
    double totalPriceOfService;
    final double  PVM = 0.21;
    Scanner userInput;
    User user;

    public BookRentServicesStrategy()
    {
        booksWishList = new ArrayList<>();
        bookInfo = new BookInformation();
        pickedBooks = new ArrayList<>();
        books = new ArrayList<>();
        userInput = new Scanner(System.in);
        totalPriceOfService = 0;
        user = null;
    }

    // Main algorithm, take costumer through renting service process
    @SuppressWarnings("Duplicates")
    public final void fulfillBookOrder(AgeGroupDiscount ageDiscount)
    {
        loginUser();
        updateBookCatalog();
        ageDiscount.getServiceInformation(books);
        bookCatalogSearch();
        calculatePrice();
        ageDiscount.applyDiscount(userInput, totalPriceOfService, pickedBooks);
        ageDiscount.sendEmailWhenReady(pickedBooks);
        logOutUser();
        System.out.println("House is built.");
    }

    // Logs in user to library service system
    @SuppressWarnings("Duplicates")
    private void loginUser()
    {
        JSONParser parser = new JSONParser();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader("C:\\Users\\manta\\IdeaProjects\\SafeLoan\\src\\mif\\ps\\psp\\FirstAssignment\\Data\\Users.json"));
            while(true) {
                Scanner input = new Scanner(System.in);
                System.out.println("Please enter your username & password");
                System.out.print("Your username: ");
                String username = input.nextLine();
                System.out.print("Your password: ");
                String password = input.nextLine();

                for (Object o : a) {
                    JSONObject jsonObject = (JSONObject) o;

                    if (((String) jsonObject.get("Username")).contains(username) && ((String) jsonObject.get("Password")).contains(password)) {
                        user = new User();
                        this.user.setEmail((String) jsonObject.get("Email"));
                        this.user.setName((String) jsonObject.get("Name"));
                        this.user.setSurname((String) jsonObject.get("Surname"));
                        this.user.setUsername(username);
                        this.user.setPassword(password);
                        this.user.setAge((long)jsonObject.get("Age"));

                        System.out.println("Successfully find user, logging in ..");
                        break;
                    }

                }
                if(user != null)
                    break;
                else
                    System.out.println("====== Access Denied! Wrong Password, Please try again!======");
            }
        }catch (Exception ex)
        {
            System.out.println("Something went terribly wrong while logging in! It's not your fault :)" + ex.toString());
        }
    }

    // Reads JSON data from json file and updates book catalog
    @SuppressWarnings("Duplicates")
    private boolean updateBookCatalog()
    {
        JSONParser parser = new JSONParser();

        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader("C:\\Users\\manta\\IdeaProjects\\SafeLoan\\src\\mif\\ps\\psp\\FirstAssignment\\Data\\BookINFO.json"));
            for (Object o : a) {
                JSONObject jsonObject = (JSONObject) o;
                bookInfo = new BookInformation();
                bookInfo.setAuthorName((String) jsonObject.get("author"));
                bookInfo.setBookTitle((String) jsonObject.get("name"));
                bookInfo.setGenre((String) jsonObject.get("genre_s"));
                bookInfo.setPageNumber((Long)jsonObject.get("pages_i"));
                bookInfo.setPublishDate((String) jsonObject.get("publish_date"));
                bookInfo.setShortSummary((String) jsonObject.get("short_summary"));
                bookInfo.setBookPrimePrice((double)jsonObject.get("prime_Price"));
                bookInfo.setInStock((boolean) jsonObject.get("inStock"));
                bookInfo.setBookID(Integer.parseInt((String) jsonObject.get("id")));

                books.add(bookInfo);
            }
        }catch (ParseException ex)
        {
            System.out.println("Was unable to parse file!");
            return false;
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File doesn't exist!");
            return false;
        }
        catch (IOException ex)
        {
            System.out.println("File parsing doesn't work!");
            return false;
        }

        return true;
    }

    //Searches for books in library
    @SuppressWarnings("Duplicates")
    private void bookCatalogSearch()
    {
        System.out.println("Select number of books you want to rent. Keep in mind you can take up to 7 books in one go");
        Scanner input = new Scanner(System.in);

        int numOfBooks = 0;
        int numOfIter = 0;
        numOfBooks = input.nextInt();
        input.nextLine();

        while(numOfIter < numOfBooks)
        {
            booksWishList = new ArrayList<>();
            System.out.println("Please enter the name of the book. To quit adding press 0");

            String inputBookName = input.nextLine();

            for (BookInformation book : books)
            {
                if(book.getBookTitle().contains(inputBookName))
                {
                    booksWishList.add(book);
                }
            }

            System.out.println("By the name: " + inputBookName + ", books found " + booksWishList.size());
            System.out.println("Books list is:");

            int wishListBookNumber = 0;
            for(BookInformation wishBook : booksWishList)
            {
                System.out.println("---------------------------------------------------");
                System.out.println("Book Number in wishlist: " + wishListBookNumber);
                System.out.println("Book Title is: " + wishBook.getBookTitle());
                System.out.println("Book Author: " + wishBook.getAuthorName());
                System.out.println("Book Genre: " + wishBook.getGenre());
                System.out.println("Book Published in: " + wishBook.getPublishDate());
                System.out.println("Page Number: " + wishBook.getPageNumber());
                System.out.println("Short Summary: " + wishBook.getShortSummary());
                System.out.println("Is in stock: " + wishBook.isInStock());
                System.out.println("---------------------------------------------------");
                System.out.println("Have you been searching for this book? Do you want it to add to book rent list? If yes to all answer, else no");
                String decision = input.nextLine();
                if(decision.contains("Yes"))
                {
                    System.out.print("Enter the date when you are going to take a book (MM/DD/YYYY): ");
                    String startDate = input.nextLine();
                    System.out.print("Enter the date when you are going to bring back a book (MM/DD/YYYY): ");
                    String expDate = input.nextLine();
                    wishBook.setBookExpiredate(expDate);
                    wishBook.setStartdate(startDate);
                    System.out.println("Successfully added!");
                    pickedBooks.add(wishBook);
                }
                else
                {
                    System.out.println("Looking for other books!");
                }
                wishListBookNumber++;

            }
            numOfIter++;
        }

    }

    //Calculates the total price of service, without any discount
    @SuppressWarnings("Duplicates")
    private void calculatePrice()
    {
        System.out.println("Calculating the price....");
        System.out.println("----------------------------------------------");
        System.out.println("Calculating the total book rent cost...");

        //Book rent price per day depends on the cost of book prime price. The more book cost, the bigger rent price would be
        double percentOfCost = 0.17;
        //Total price of renting all the books that user have picked
        totalPriceOfService = 0;

        //Total days book are going to be taken
        int dateDifInDays;

        //Date for calculating day book are going to be taken
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDay = null;
        Date endDay = null;
        long days;

        for(BookInformation bookInfo : pickedBooks)
        {
            days = 1;
            try {
                System.out.println("Starting Date: " + bookInfo.getStartdate());
                System.out.println("Ending Date: " + bookInfo.getBookExpiredate());
                startDay = df.parse(bookInfo.getStartdate());
                endDay = df.parse(bookInfo.getBookExpiredate());
                long diff = endDay.getTime() - startDay.getTime();
                days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

            }catch (Exception ex)
            {
                System.out.println("Wrong date format!" + ex.toString());
            }

            System.out.println("Book prime price: " + bookInfo.getBookPrimePrice() + " Percent of cost: " + percentOfCost + " Days are: " + days);
            double costAfterPercent =  bookInfo.getBookPrimePrice() * percentOfCost * days;
            totalPriceOfService += costAfterPercent;
        }

        double totalPriceOfServiceWithoutPVM = totalPriceOfService - totalPriceOfService * PVM;

        //Printing Prices of rent service with and without PVM
        System.out.println("------------------------Before Discount----------------------");
        System.out.println("Total rent price with PVM: " + totalPriceOfService);
        System.out.println("Total rent price without PVM: " + totalPriceOfServiceWithoutPVM);
        System.out.println("-------------------------------------------------------------");
    }

    //Logs out user from library service system
    private void logOutUser()
    {
        System.out.println("Logging of the system, We hope you are comming back soon :) ");
    }

}
