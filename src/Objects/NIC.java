/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author R3DN3T
 */
public class NIC {
    private String NICName;
    private String NICAddress;
    public NIC(String nn,String na){
        this.NICName = nn;
        this.NICAddress = na;
    }
    public String GetNICName(){return this.NICName;}
    public String GetNICAddress(){return this.NICAddress;}
}