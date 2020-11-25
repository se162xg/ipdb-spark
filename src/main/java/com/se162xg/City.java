package com.se162xg;

import com.google.common.net.InetAddresses;
import java.net.Inet6Address;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.InputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class City {

    private Reader reader;
    
    private Reader readerv6;

    public City(String name) throws IOException,InvalidDatabaseException {
        Reader tmp = new Reader(name);
	if ( tmp.isIPv4() ) {
	    this.reader = tmp;
	} else {
	    this.readerv6 = tmp;
	}
    }
    
    public City(String name, String namev6) throws IOException,InvalidDatabaseException {
        this.reader = new Reader(name);
	this.readerv6 = new Reader(namev6);
    }

    public City(InputStream in) throws IOException,InvalidDatabaseException {
        Reader tmp = new Reader(in);
	if ( tmp.isIPv4() ) {
            this.reader = tmp;
        } else {
            this.readerv6 = tmp;
        }
    }

    public City(InputStream in, InputStream inv6) throws IOException,InvalidDatabaseException {
        this.reader = new Reader(in);
	this.readerv6 = new Reader(inv6);
    }

    private boolean isIPv6Enabled() {
        return ( this.readerv6 != null );
    }

    private boolean isIPv4Enabled() {
        return ( this.reader != null );
    }
 
    public static boolean isIPv4Format(String ip) {
        Pattern pattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
        Matcher matcher = pattern.matcher(ip);
        return matcher.find();
    }

    public CityInfo findInfo(String addr) {
	try
	{
            return this.findInfo(addr, "CN");
	} 
	catch (Exception e) 
	{
	    e.printStackTrace();
	    return null;
	}
    }

    public CityInfo findInfo(String addr, String language) throws IPFormatException, InvalidDatabaseException {
	String[] data;
	if ( this.isIPv4Format(addr) ) {
            data=this.reader.find(addr, language);
	} else if( !this.isIPv6Enabled() ) {
	    data=null;
	} else if( !this.isIPv4Enabled() ) {
	    data=this.readerv6.find(addr, language);
        } else {	    
	    Inet6Address addrv6 = (Inet6Address)InetAddresses.forString(addr);
	    if ( InetAddresses.is6to4Address(addrv6) ) {
                data=this.reader.find(InetAddresses.get6to4IPv4Address(addrv6).getHostAddress(), language);
	    } else {		
                data=this.readerv6.find(addr, language);
	    } 
	}
	return new CityInfo(data);
    }

    public int buildTime() {
        return this.reader.getBuildUTCTime();
    }

    public boolean isIPv4() {
        return this.reader.isIPv4();
    }

    public boolean isIPv6() {
        return this.reader.isIPv6();
    }

    public String fields() {
        return Arrays.toString(this.reader.getSupportFields());
    }

    public String languages() {
        return this.reader.getSupportLanguages();
    }
}
