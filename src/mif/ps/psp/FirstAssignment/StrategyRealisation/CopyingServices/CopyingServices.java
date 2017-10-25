package mif.ps.psp.FirstAssignment.StrategyRealisation.CopyingServices;

import mif.ps.psp.FirstAssignment.StrategyRealisation.AgeGroupDiscount.AgeGroupDiscount;
import mif.ps.psp.FirstAssignment.StrategyRealisation.CopyingServices.PaperFormat.StandartPaperFormat;
import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.Model.BookInformation;
import mif.ps.psp.FirstAssignment.TemplateMethodRealisation.Model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CopyingServices {
    double totalPriceOfService;
    List<BookInformation> books;
    List<BookInformation> pickedBooks;
    String printerModel;
    Scanner userInput;
    Boolean colored;
    User user;

    public CopyingServices()
    {
        userInput = new Scanner(System.in);
        pickedBooks = new ArrayList<>();
        books = new ArrayList<>();
        totalPriceOfService = 0;
        user = null;
        colored = false;
    }

    //Template Method
    @SuppressWarnings("Duplicates")
    public void fulfillCopyingService(AgeGroupDiscount ageGroupDiscount, StandartPaperFormat standartPaperFormat)
    {
        loginUser();
        updateBookCatalog();
        ageGroupDiscount.getServiceInformation(books);
        bookCatalogSearch();
        standartPaperFormat.pickPrinter(userInput, printerModel, colored);
        standartPaperFormat.calculatePrice(totalPriceOfService, pickedBooks);
        ageGroupDiscount.applyDiscount(userInput, totalPriceOfService, pickedBooks);
        standartPaperFormat.sendFileToPrinter(printerModel);
        ageGroupDiscount.sendEmailWhenReady(pickedBooks);
        logOutUser();
    }

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

    @SuppressWarnings("Duplicates")
    private boolean updateBookCatalog()
    {
        JSONParser parser = new JSONParser();
        BookInformation bookInfo;

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


    @SuppressWarnings("Duplicates")
    private void bookCatalogSearch()
    {
        System.out.println("Select number of books you want to print. Unlike in renting services you can pick any number of books");
        Scanner input = new Scanner(System.in);
        List <BookInformation> booksWishList;

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

    private void logOutUser()
    {

        System.out.println("Logging of the system, We hope you are comming back soon :) ");
    }
}
