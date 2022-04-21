package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("         <!-- CSS only -->\n");
      out.write("        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n");
      out.write("        <!-- JavaScript Bundle with Popper -->\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\" crossorigin=\"anonymous\"></script>\n");
      out.write("        <style>\n");
      out.write("            .divider:after,\n");
      out.write("            .divider:before {\n");
      out.write("              content: \"\";\n");
      out.write("              flex: 1;\n");
      out.write("              height: 1px;\n");
      out.write("              background: #eee;\n");
      out.write("            }\n");
      out.write("            .h-custom {\n");
      out.write("              height: calc(100% - 73px);\n");
      out.write("            }\n");
      out.write("            @media (max-width: 450px) {\n");
      out.write("              .h-custom {\n");
      out.write("                height: 100%;\n");
      out.write("              }\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        </style>\n");
      out.write("        \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("  <section class=\"vh-100\">\n");
      out.write("  <div class=\"container-fluid h-custom\">\n");
      out.write("    <div class=\"row d-flex justify-content-center align-items-center h-100\">\n");
      out.write("      <div class=\"col-md-9 col-lg-6 col-xl-5\">\n");
      out.write("        <img src=\"https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp\" class=\"img-fluid\"\n");
      out.write("          alt=\"Sample image\">\n");
      out.write("      </div>\n");
      out.write("      <div class=\"col-md-8 col-lg-6 col-xl-4 offset-xl-1\">\n");
      out.write("        <form>\n");
      out.write("          <div class=\"d-flex flex-row align-items-center justify-content-center justify-content-lg-start\">\n");
      out.write("            <p class=\"lead fw-normal mb-0 me-3\">Sign in with</p>\n");
      out.write("            <button type=\"button\" class=\"btn btn-primary btn-floating mx-1\">\n");
      out.write("              <i class=\"fab fa-facebook-f\"></i>\n");
      out.write("            </button>\n");
      out.write("\n");
      out.write("            <button type=\"button\" class=\"btn btn-primary btn-floating mx-1\">\n");
      out.write("              <i class=\"fab fa-twitter\"></i>\n");
      out.write("            </button>\n");
      out.write("\n");
      out.write("            <button type=\"button\" class=\"btn btn-primary btn-floating mx-1\">\n");
      out.write("              <i class=\"fab fa-linkedin-in\"></i>\n");
      out.write("            </button>\n");
      out.write("          </div>\n");
      out.write("\n");
      out.write("          <div class=\"divider d-flex align-items-center my-4\">\n");
      out.write("            <p class=\"text-center fw-bold mx-3 mb-0\">Or</p>\n");
      out.write("          </div>\n");
      out.write("\n");
      out.write("          <!-- Email input -->\n");
      out.write("          <div class=\"form-outline mb-4\">\n");
      out.write("            <input type=\"email\" id=\"form3Example3\" class=\"form-control form-control-lg\"\n");
      out.write("              placeholder=\"Enter a valid email address\" />\n");
      out.write("            <label class=\"form-label\" for=\"form3Example3\">Email address</label>\n");
      out.write("          </div>\n");
      out.write("\n");
      out.write("          <!-- Password input -->\n");
      out.write("          <div class=\"form-outline mb-3\">\n");
      out.write("            <input type=\"password\" id=\"form3Example4\" class=\"form-control form-control-lg\"\n");
      out.write("              placeholder=\"Enter password\" />\n");
      out.write("            <label class=\"form-label\" for=\"form3Example4\">Password</label>\n");
      out.write("          </div>\n");
      out.write("\n");
      out.write("          <div class=\"d-flex justify-content-between align-items-center\">\n");
      out.write("            <!-- Checkbox -->\n");
      out.write("            <div class=\"form-check mb-0\">\n");
      out.write("              <input class=\"form-check-input me-2\" type=\"checkbox\" value=\"\" id=\"form2Example3\" />\n");
      out.write("              <label class=\"form-check-label\" for=\"form2Example3\">\n");
      out.write("                Remember me\n");
      out.write("              </label>\n");
      out.write("            </div>\n");
      out.write("            <a href=\"#!\" class=\"text-body\">Forgot password?</a>\n");
      out.write("          </div>\n");
      out.write("\n");
      out.write("          <div class=\"text-center text-lg-start mt-4 pt-2\">\n");
      out.write("            <button type=\"button\" class=\"btn btn-primary btn-lg\"\n");
      out.write("              style=\"padding-left: 2.5rem; padding-right: 2.5rem;\">Login</button>\n");
      out.write("            <p class=\"small fw-bold mt-2 pt-1 mb-0\">Don't have an account? <a href=\"#!\"\n");
      out.write("                class=\"link-danger\">Register</a></p>\n");
      out.write("          </div>\n");
      out.write("\n");
      out.write("        </form>\n");
      out.write("      </div>\n");
      out.write("    </div>\n");
      out.write("  </div>\n");
      out.write("  <div class=\"d-flex flex-column flex-md-row text-center text-md-start justify-content-between py-4 px-4 px-xl-5 bg-primary\">\n");
      out.write("    <!-- Copyright -->\n");
      out.write("    <div class=\"text-white mb-3 mb-md-0\">\n");
      out.write("      Copyright © 2020. All rights reserved.\n");
      out.write("    </div>\n");
      out.write("    <!-- Copyright -->\n");
      out.write("\n");
      out.write("    <!-- Right -->\n");
      out.write("    <div>\n");
      out.write("      <a href=\"#!\" class=\"text-white me-4\">\n");
      out.write("        <i class=\"fab fa-facebook-f\"></i>\n");
      out.write("      </a>\n");
      out.write("      <a href=\"#!\" class=\"text-white me-4\">\n");
      out.write("        <i class=\"fab fa-twitter\"></i>\n");
      out.write("      </a>\n");
      out.write("      <a href=\"#!\" class=\"text-white me-4\">\n");
      out.write("        <i class=\"fab fa-google\"></i>\n");
      out.write("      </a>\n");
      out.write("      <a href=\"#!\" class=\"text-white\">\n");
      out.write("        <i class=\"fab fa-linkedin-in\"></i>\n");
      out.write("      </a>\n");
      out.write("    </div>\n");
      out.write("    <!-- Right -->\n");
      out.write("  </div>\n");
      out.write("</section>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
