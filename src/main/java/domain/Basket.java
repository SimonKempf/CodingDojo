package domain;

import java.util.Arrays;

public class Basket {

    public static final double BASE_PRICE = 8.0;

    public double getPrice(Book... books) {
        return getDiscountRate(books);
    }

    public double getDiscountRate(final Book... books) {
        final long distinctBooksNumber = getDistinctBookNumber(books);
        final double price = computeDiscount(books.length, distinctBooksNumber);
        return price;

    }

    private double computeDiscount(int totalBooksNumber, long distinctBooksNumber) {
        final long nonDistinctBook = totalBooksNumber - distinctBooksNumber;
        double discountPrice = 0;
        if (distinctBooksNumber == 1) {
            discountPrice = 1 * BASE_PRICE;
        } else if (distinctBooksNumber == 2) {
            discountPrice = 2 * BASE_PRICE * 0.95;
        } else if (distinctBooksNumber == 3) {
            discountPrice = 3 * BASE_PRICE * 0.90;

        } else if (distinctBooksNumber == 4) {
            discountPrice = 4 * BASE_PRICE * 0.80;

        } else if (distinctBooksNumber == 5) {
            discountPrice =  5 * BASE_PRICE * 0.75;
        }
        discountPrice = discountPrice + (nonDistinctBook * BASE_PRICE);
        return discountPrice;
    }

    private long getDistinctBookNumber(Book[] books) {
        return Arrays.stream(books).map(Book::getTitle).distinct().count();
    }
}
