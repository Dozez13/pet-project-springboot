<%@ tag import="com.example.task16.service.util.ImageUtil" %>
<%@ tag language="java" body-content="empty" pageEncoding="utf-8" %>
<%@ attribute name="image" required="true" rtexprvalue="true" type="java.lang.Object" %>
<%

out.print(ImageUtil.getBase64String((byte[])image));
%>