//package com.hilquiascamelo.facialrecognitionsystem.domain.controller;
//
//import org.springframework.boot.web.error.ErrorAttributeOptions;
//import org.springframework.boot.web.servlet.error.ErrorAttributes;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.ServletWebRequest;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.ModelAndView;
//
//import jakarta.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Basic Controller which is called for unhandled errors
// */
//@Controller
//public class AppErrorController {
//
//    private final ErrorAttributes errorAttributes;
//    private final static String ERROR_PATH = "/error";
//
//    public AppErrorController(ErrorAttributes errorAttributes) {
//        this.errorAttributes = errorAttributes;
//    }
//
//    /**
//     * Supports the HTML Error View
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = ERROR_PATH, produces = "text/html")
//    public ModelAndView errorHtml(HttpServletRequest request) {
//        Map<String, Object> errorAttributes = getErrorAttributes(request, false);
//        return new ModelAndView("/errors/error", errorAttributes);
//    }
//
//    /**
//     * Supports other formats like JSON, XML
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = ERROR_PATH)
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
//        Map<String, Object> body = getErrorAttributes(request, true);
//        HttpStatus status = getStatus(request);
//        return new ResponseEntity<>(body, status);
//    }
//
//    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
//        WebRequest webRequest = new ServletWebRequest(request);
//        ErrorAttributeOptions options = includeStackTrace
//                ? ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE)
//                : ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE);
//        return this.errorAttributes.getErrorAttributes(webRequest, options);
//    }
//
//    private HttpStatus getStatus(HttpServletRequest request) {
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        if (statusCode != null) {
//            try {
//                return HttpStatus.valueOf(statusCode);
//            } catch (Exception ex) {
//            //TODO
//            }
//        }
//        return HttpStatus.INTERNAL_SERVER_ERROR;
//    }
//}
