/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas;

import java.util.Scanner;

/**
 *
 * @author Arto
 */

public class BasicIO implements IO{
    
    private Scanner scanner;
    
    public BasicIO(){
        scanner = new Scanner(System.in);
    }

    @Override
    public void print(Object text) {
        System.out.println(text);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }
    
    
}
