package sample.Repositories;

import sample.ConnectDB;
import sample.Controllers.Payments;
import sample.Models.Guests;
import sample.Models.Payment;
import sample.Models.View.PaymentModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentsRepository {

    private static PaymentModel parseFromRes(ResultSet res) throws Exception {
        int payment_id = res.getInt("payment_id");
        String firstname = res.getString("first_name");
        String lastname = res.getString("last_name");
        Date date = res.getDate("checkin_date");
        double price = res.getDouble("price");
        int is_payed = res.getInt("is_payed");


        return new PaymentModel(payment_id, firstname, lastname, date, price, is_payed);
    }





    }
}
