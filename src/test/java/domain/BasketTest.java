package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BasketTest {

    @org.junit.jupiter.api.Test
    void getPrice_GivenOneBook_ThenReturnsBasePrice() {
        Basket sut = new Basket();
        double current = sut.getPrice(new Book("hp1"));
        assertEquals(Basket.BASE_PRICE, current);
    }

    @org.junit.jupiter.api.Test
    void getPrice_GivenTwiceTheSameBook_ThenReturnsTwiceTheBasePrice() {
        Basket sut = new Basket();
        double current = sut.getPrice(new Book("hp1"), new Book("hp1"));
        assertEquals(Basket.BASE_PRICE * 2, current);
    }

    @org.junit.jupiter.api.Test
    void getPrice_GivenTwoDifferentBooks_ThenReturnsTwiceTheBasePriceWith5PercentDiscount() {
        Basket sut = new Basket();
        double current = sut.getPrice(new Book("hp1"), new Book("hp2"));
        assertEquals(Basket.BASE_PRICE * 2 * .95, current);
    }

    @org.junit.jupiter.api.Test
    void getPrice_GivenThreeDifferentBooks_ThenReturnsThreeTimesTheBasePriceWith10PercentDiscount() {
        Basket sut = new Basket();
        double current = sut.getPrice(new Book("hp1"), new Book("hp2"), new Book("hp3"));
        assertEquals(Basket.BASE_PRICE * 3 * .9, current);
    }

    @org.junit.jupiter.api.Test
    void getPrice_GivenTwoDistinctBooksOutOfThree_ThenReturnsTwoTimesTheBasePriceWith5PercentDiscountPlusTheBasePrice() {
        Basket sut = new Basket();
        double current = sut.getPrice(new Book("hp1"), new Book("hp2"), new Book("hp2"));
        assertEquals(Basket.BASE_PRICE * 2 * .95 + Basket.BASE_PRICE, current);
    }
}