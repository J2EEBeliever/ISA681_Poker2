/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author bwoltemate
 */
public class ShowGame extends ActionSupport implements LoginRequired {
    private static final long serialVersionUID = 2015_12_14_005L;
     @Override
     public String execute()
  {
      return SUCCESS;
  }
    
}
