package com.datomata;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.oracle.tools.packager.Log;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Utilities {
    static Logger log = Logger.getLogger(Utilities.class.getName());

    public static void printAWSException(AmazonServiceException ase) {
        Log.info(String.format("%s exception thrown", ase.getMessage()));
        System.out.println("Caught an AmazonServiceException, which" +
                " means your request made it " +
                "to Amazon S3, but was rejected with an error response" +
                " for some reason.");
        System.out.println("Error Message:    " + ase.getMessage());
        System.out.println("HTTP Status Code: " + ase.getStatusCode());
        System.out.println("AWS Error Code:   " + ase.getErrorCode());
        System.out.println("Error Type:       " + ase.getErrorType());
        System.out.println("Request ID:       " + ase.getRequestId());
    }

    public static void printException(IOException ioex) {
        Log.info(String.format("%s exception thrown", ioex.getMessage()));
        System.out.println(String.format("%s : %s", ioex.getMessage(), ioex.getStackTrace()));
    }

    public static void printException(Exception ioex) {
        Log.info(String.format("%s exception thrown", ioex.getMessage()));
        System.out.println(String.format("%s : %s", ioex.getMessage(), ioex.getStackTrace()));
    }

    public static void printException(AmazonClientException ioex) {
        Log.info(String.format("%s exception thrown", ioex.getMessage()));
        System.out.println(String.format("%s : %s", ioex.getMessage(), ioex.getStackTrace()));
    }

    public static void printToConsole(DebugLevel level, String... arguments) {
        for(String argument: arguments) {
            System.out.println(argument);
        }
    }
}
