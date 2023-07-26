package com.github.golovnyakpa.hw9;

import com.github.golovnyakpa.hw9.adapter.PayPalToSitePaymentAdapter;
import com.github.golovnyakpa.hw9.adapter.payment_sytems.PayPal;
import com.github.golovnyakpa.hw9.adapter.payment_sytems.PayPalImpl;
import com.github.golovnyakpa.hw9.bridge.color.BlueColor;
import com.github.golovnyakpa.hw9.bridge.color.Color;
import com.github.golovnyakpa.hw9.bridge.color.RedColor;
import com.github.golovnyakpa.hw9.bridge.shape.Rectangle;
import com.github.golovnyakpa.hw9.bridge.shape.Shape;
import com.github.golovnyakpa.hw9.bridge.shape.Triangle;
import com.github.golovnyakpa.hw9.decorator.Aperitif;
import com.github.golovnyakpa.hw9.decorator.LargePortion;
import com.github.golovnyakpa.hw9.decorator.Order;
import com.github.golovnyakpa.hw9.decorator.OrdinaryOrder;
import com.github.golovnyakpa.hw9.facade.NotificationFacade;
import com.github.golovnyakpa.hw9.facade.NotificationType;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        decoratorDemo();
        System.out.println("\n");
        bridgeDemo();
        System.out.println("\n");
        facadeDemo();
        System.out.println("\n");
        adapterDemo();
    }

    private static void decoratorDemo() {
        Order ordinaryOrder =
                new OrdinaryOrder(Arrays.asList("Caesar salad", "Chicken"), Arrays.asList("Long Island", "Cola"));
        Order aperitifOrd = new Aperitif(ordinaryOrder);
        Order largeOrd = new LargePortion(aperitifOrd);
        System.out.println(largeOrd.drinks());
        System.out.println(largeOrd.dishes());
    }

    private static void bridgeDemo() {
        Color red = new RedColor();
        Color blue = new BlueColor();
        Shape redRectangle = new Rectangle(red);
        Shape blueRectangle = new Rectangle(blue);
        Shape redTriangle = new Triangle(red);
        System.out.println(redTriangle.draw());
        System.out.println(blueRectangle.draw());
        System.out.println(redRectangle.draw());
    }

    public static void facadeDemo() {
        System.out.println(
                new NotificationFacade()
                        .sendNotification(NotificationType.EMAIL, "Alice", "Bob", "Hi, Alice! How are you?"
                        )
        );
        System.out.println(
                new NotificationFacade()
                        .sendNotification(NotificationType.PUSH, "543546", "Delivery", "Your pizza is on the way!"
                        )
        );
        System.out.println(
                new NotificationFacade()
                        .sendNotification(NotificationType.SMS, "Petr Ivanov", "Navigator", "You are 10 min far from home"
                        )
        );
    }

    private static void adapterDemo() {
        PayPal payPal = new PayPalImpl();
        System.out.println("Running payment at site with pay pal started...");
        new PayPalToSitePaymentAdapter(payPal).runPayment(5);
    }

}
