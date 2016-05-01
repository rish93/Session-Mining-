package com.net.connect.proxy;

import javax.swing.JOptionPane;

public class ProxyClientLogin
{
  public static void main(String arg[])
  {
  try
  {
  ProxyLoginFrame frame=new ProxyLoginFrame();
  frame.setSize(300,100);
  frame.setVisible(true);
  }
  catch(Exception e)
  {
	  JOptionPane.showMessageDialog(null, e.getMessage());}
  }
}