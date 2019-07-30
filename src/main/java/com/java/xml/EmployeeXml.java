package com.java.xml;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

/**
 * 描述    :解析xml
 * Author :Qing_X
 * Date   :2019-04-27 19:23
 */
public class EmployeeXml {
    public static void main(String[] args) throws IOException, SAXException {
        Digester digester = new Digester();
        digester.addObjectCreate("employee", Employee.class);
        digester.addSetProperties("employee");
        digester.addObjectCreate("employee/office", Office.class);
        digester.addSetNext("employee/office", "addOffice");
        digester.addObjectCreate("employee/office/address", Address.class);
        digester.addSetProperties("employee/office/address");
        digester.addSetNext("employee/office/address", "setAddress");
        Employee employee = digester.parse(EmployeeXml.class.getClassLoader().getResourceAsStream("employee.xml"));
        System.out.println(employee.getName());
        System.out.println(employee.getAge());
        System.out.println(employee.getOffices().size());
        System.out.println(employee.getOffices().get(0).getAddress().getName());
        System.out.println(employee.getOffices().get(1).getAddress().getName());
    }
}
