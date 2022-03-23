/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validation;

import model.Category;
import model.Product;
import model.SubCategory;

/**
 *
 * @author PCDELL
 */
public class Validation {
    public static boolean CheckCateKhacNull(Category c){
          int count = 0;
          for (SubCategory sub : c.getSubs()) {
              for (Product p : sub.getProd()) {
                  count+=1;
              }
          }
          if(count!=0)return true;
          else return false;
    }
    
    public static boolean CheckSubKhacNull(SubCategory sub){
          int count = 0;
          
              for (Product p : sub.getProd()) {
                  count+=1;
              }
          
          if(count!=0)return true;
          else return false;
    }
}
