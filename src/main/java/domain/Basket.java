package domain;

import java.util.*;

public class Basket {

    public static final double BASE_PRICE = 8.0;

    public double getPrice(Book... books) {
        return getDiscountRate(books);
    }

    public double getDiscountRate(final Book... books) {
//        Map<String, List<Book>> booksByTitleTest = Arrays.stream(books).collect(Collectors.groupingBy(Book::getTitle));

          // why this is not working ????
//        Map<String, List<Book>> booksByTitle = new HashMap<>();
//        List<Book> bookList = Arrays.asList(books);
//        for (Book book : bookList) {
//            if (!booksByTitle.containsKey(book.getTitle())) {
//                booksByTitle.put(book.getTitle(), Arrays.asList(book));
//            } else {
//                booksByTitle.get(book.getTitle()).add(book);
//            }
//        }
//        System.out.println(booksByTitle);
//        -->
//        "h1" -> [B, B]
//        "h2" -> [B, B]
//        "h3" -> [B]
//        "h4" -> [B]
//        "h5" -> [B]

        // OR
        Map<String, Integer> bookNumbersByTitle = getNumberOfBooksByTitle(books);
//      -->
//        "h1" -> 2
//        "h2" -> 2
//        "h3" -> 1
//        "h4" -> 1
//        "h5" -> 1

//        transform to

//        [ ["h1", "h2", "h3", "h4", "h5"], ["h1","h2"]]

        return computePrice(getDistinctListOfBooks(bookNumbersByTitle));
    }

    private List<Set<String>> getDistinctListOfBooks(Map<String, Integer> bookNumbersByTitle) {
        List<Set<String>> distinctBookSets = new ArrayList<Set<String>>();
        int maxValueInMap = Collections.max(bookNumbersByTitle.values());
        for(int i = 0; i < maxValueInMap; i++ ) {
            Set<String> distinctBooks = new HashSet<>();
            for(String key : bookNumbersByTitle.keySet()){
                Integer value = bookNumbersByTitle.get(key);
                if(bookNumbersByTitle.get(key) != 0) {
                    distinctBooks.add(key);
                    bookNumbersByTitle.put(key, value -1);
                }
            }
            distinctBookSets.add(distinctBooks);
        }
        return distinctBookSets;
    }

    private Map<String, Integer> getNumberOfBooksByTitle(Book[] books) {
        Map<String, Integer> bookSummary = new HashMap<>();
        for (Book book : books) {
            String key = book.getTitle();
            if (!bookSummary.containsKey(key)) {
                bookSummary.put(key, 1);
            } else {
                bookSummary.put(key, bookSummary.get(key)+1);
            }
        }
        return bookSummary;
    }

    private double computePrice(List<Set<String>> distinctBooks) {
        double price = 0;
        for (Set<String> distinctBook : distinctBooks) {
            price += computePriceWithDiscount(distinctBook.size());
        }
        // TODO do with stream().map
        return price;
    }

    private double computePriceWithDiscount(int numberOfDistinctBooks) {
        double discountPrice = 0;
        if (numberOfDistinctBooks == 1) {
            discountPrice = 1 * BASE_PRICE;
        } else if (numberOfDistinctBooks == 2) {
            discountPrice = 2 * BASE_PRICE * 0.95;
        } else if (numberOfDistinctBooks == 3) {
            discountPrice = 3 * BASE_PRICE * 0.90;
        } else if (numberOfDistinctBooks == 4) {
            discountPrice = 4 * BASE_PRICE * 0.80;
        } else if (numberOfDistinctBooks == 5) {
            discountPrice =  5 * BASE_PRICE * 0.75;
        }
        return discountPrice;
    }
}
