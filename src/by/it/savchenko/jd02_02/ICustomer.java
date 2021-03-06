package by.it.savchenko.jd02_02;

public interface ICustomer {

    void enterToMarket();    //вошел в магазин (мгновенно)

    void chooseGoods();      //выбрал товар (от 0,5 до 2 секунд)

    void goToQueue();

    void putGoodsToBasket();

    void goOut();            //отправился на выход(мгновенно)

}
