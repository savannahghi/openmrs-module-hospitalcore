/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */


package org.openmrs.module.hospitalcore.matcher;


import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openmrs.api.context.Context;

/**
 * The class represents different utility methods that are used by the registration module
 * like generation of identifiers of different formats, adding checkdigits
 */
public class RegistrationUtils
{

    /*
     * Creates a new Patient Identifier: <prefix>YYMMDDhhmmxxx-checkdigit
     *     where prefix = global_prop (registration.identifier_prefix)
     *                    YY = two char representation of current year e.g. 2009 - 09
     *                    MM = current month. e.g. January - 1; December - 12
     *                    DD = current day of month e.g. 20
     *                    hh = hour of day e.g. 10PM - 22
     *                    mm = minutes e.g. 10:12 - 12
     *                    xxx = three random digits e.g. from 0 - 999
     *                    checkdigit = using the Lunh Algorithm
     */
    public static String getNewIdentifier()
    {
        Calendar now = Calendar.getInstance();
        String shortName = Context.getAdministrationService().getGlobalProperty( "registration.identifier_prefix" );
        String noCheck = shortName
            + String.valueOf( now.get( Calendar.YEAR ) ).substring( 2, 4 )
            + String.valueOf( now.get( Calendar.MONTH ) + 1 )
            + String.valueOf( now.get( Calendar.DATE ) )
            + String.valueOf( now.get( Calendar.MINUTE ) )
            + String.valueOf( new Random().nextInt( 999 ) );
        return noCheck + "-" + getCheckdigit( noCheck );
    }

    public static String getBase30Identifier( int length, String startID )
    {
    	//ghanshyam 26/06/2012 tag DLS_DEAD_LOCAL_STORE code String noCheck = new String();
    	// String noCheck = new String();
        String validChars = "0123456789ACDEFGHJKLMNPRSTUVWXY";
        boolean allY = false;
        for ( int i = 0; i < startID.length(); i++ )
        {
            if ( startID.charAt( i ) == 'Y' )
            {
                allY = true;
            } else
            {
                allY = false;
                break;
            }
        }
        if ( startID.length() > ( length ) )
        {
            System.out.println( "your identifier length is more than fixed langth" );
            return ( "" );
        } else
        {
            for ( int index = startID.length() - 1; index >= 0; index-- )
            {
                char toIncrement = startID.charAt( index );
                int diff = ( validChars.length() - 1 ) - validChars.indexOf( toIncrement );
                if ( diff == 0 )
                {
                    if ( index == startID.length() - 1 )
                    {
                        startID = startID.substring( 0, index ) + validChars.charAt( 0 ) + startID.substring( index, startID.length() - 1 );//19
                    } else
                    {
                        startID = startID.substring( 0, index ) + validChars.charAt( 0 ) + startID.substring( index + 1 );//19
                        if ( startID.equals( "0" ) )
                        {
                            break;
                        }
                    }
                    continue;
                } else
                {
                    startID = startID.substring( 0, index ) + validChars.charAt( validChars.indexOf( toIncrement ) + 1 ) + startID.substring( index + 1 );//19
                    break;
                }
            }
            if ( startID.equals( "0" ) )
            {
                System.out.println( "Cannot generate more identifiers" );
            } else
            {
                if ( allY )
                {
                    startID = "1" + startID;
                }
            }
            return ( startID + "-" + getCheckdigit( startID ) );
        }
    }

    /**
     * Using the Luhn Algorithm to generate check digits
     * @param idWithoutCheckdigit
     * @return idWithCheckdigit
     */
    private static int getCheckdigit( String idWithoutCheckdigit )
    {
        String validChars = "0123456789ACDEFGHJKLMNPRSTUVWXY";
        idWithoutCheckdigit = idWithoutCheckdigit.trim().toUpperCase();
        int sum = 0;
        for ( int i = 0; i < idWithoutCheckdigit.length(); i++ )
        {
            char ch = idWithoutCheckdigit.charAt( idWithoutCheckdigit.length() - i - 1 );
            if ( validChars.indexOf( ch ) == -1 )
            {
                System.out.println( "\"" + ch + "\" is an invalid character" );
            }
            int digit = (int) ch - 48;
            int weight;
            if ( i % 2 == 0 )
            {
                weight = ( 2 * digit ) - (int) ( digit / 5 ) * 9;
            } else
            {
                weight = digit;
            }
            sum += weight;
        }
        sum = Math.abs( sum ) + 10;
        return ( 10 - ( sum % 10 ) ) % 10;
    }
    
    public static String getAgeFromBirthDate(Date birth, boolean estimate){
		String result = "";
//        var d = parseDateString(birth.substring(0,9));
//        Date d2 = parseDateString(birth);
		
		//ghanshyam 26/06/2012 tag DLS_DEAD_LOCAL_STORE code Date today = new Date();
      //  Date today = new Date();
        Calendar now = Calendar.getInstance();
        Calendar bd = Calendar.getInstance();
        bd.setTime(birth);
        
        // The number of milliseconds in one day
        long ONE_DAY = 1000 * 60 * 60 * 24;

        // Convert both dates to milliseconds
        long date1_ms = now.getTimeInMillis();
        long date2_ms = bd.getTimeInMillis();

        // Calculate the difference in milliseconds
        long difference_ms = Math.abs(date1_ms - date2_ms);
        
        //ghanshyam 6-august-2013 code review bug
        float noOfDay = difference_ms/ONE_DAY;
    	int day = Math.round(noOfDay);

        String est = "";
        
        if(estimate	){
        	est ="~";
        }
        if(bd.get(Calendar.YEAR)<now.get(Calendar.YEAR)  && day > now.getActualMaximum(Calendar.DAY_OF_YEAR)){
            int year = now.get(Calendar.YEAR) - bd.get(Calendar.YEAR); 
        	if(year==1){
        		result = est + year + " year";
            }else{
            	result = est + year + " years";
            }
        }else if( day > 31){
        	int month = 0;
        	if(bd.get(Calendar.YEAR) < now.get(Calendar.YEAR)  )
        		month = 12 - bd.get(Calendar.MONTH)  + now.get(Calendar.MONTH) ;
        	if(bd.get(Calendar.YEAR) == now.get(Calendar.YEAR)  )
        		month = now.get(Calendar.MONTH) - bd.get(Calendar.MONTH) ;
        	if(month==1){
        		result += est + month + " month";
            }else{
            	result += est + month + " months";
            }
        }else{
            if(day==1){
        		result += est + day + "day";
            }else{
            	result += est + day + " days";
            }
        }
    	return result;
    }
}
