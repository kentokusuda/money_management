package validators;

import models.Expenses;

public class Validator {


//入力はhtmlのtypeで数字に限定させる
   public static String validateExpense(Expenses e) {

       String error = new String();

       if(e.getExpense() == null || e.getExpense().equals("")){
           error ="金額を入力してください";
       }
       return error;

   }





}

//あとで消す  うまく動作するか未確認