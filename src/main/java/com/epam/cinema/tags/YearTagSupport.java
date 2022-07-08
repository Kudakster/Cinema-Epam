package com.epam.cinema.tags;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDate;

public class YearTagSupport extends SimpleTagSupport {

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        out.println("&copy; " + LocalDate.now().getYear());
    }
}
