package dev.ahmed.mytool;

import static dev.ahmed.mytool.MyMethodsForIPTool.getIpAdressAndLocationsFromUrl;


/**
 * @author Ahmed Bughra
 * @create 2022-12-14  7:54 PM
 */
public class GetIp {
            public static void main(String[] args) throws Exception {
                String url = "https://ipapi.com/documentation";
                getIpAdressAndLocationsFromUrl(url); // write txt report
            }
}





