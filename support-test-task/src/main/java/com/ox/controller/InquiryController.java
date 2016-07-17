package com.ox.controller;

import com.ox.domain.Inquiry;
import com.ox.exception.InquiryNotFoundException;
import com.ox.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class InquiryController {

    private InquiryService inquiryService;

    @Autowired
    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @RequestMapping(value = "/{customerName}/inquiries", method = RequestMethod.GET, produces = "application/json")
    public List<Inquiry> listCustomerName(@PathVariable(value = "customerName") String customerName)
            throws InquiryNotFoundException {
        List<Inquiry> inquiries = inquiryService.listCustomerName(customerName);
        if (inquiries == null) {
            throw new InquiryNotFoundException("Inquiries with customer name: " + customerName + " not found.");
        }
        return inquiries;
    }

    @RequestMapping(value = "/{customerName}/inquiries/{id}", method = RequestMethod.GET, produces = "application/json")
    public Inquiry listCustomerNameAndId(@PathVariable(value = "customerName") String customerName, @PathVariable(value = "id") Long id)
            throws InquiryNotFoundException {
        Inquiry inquiry = inquiryService.listCustomerNameAndId(customerName, id);
        if (inquiry == null) {
            throw new InquiryNotFoundException("Inquiry with customer name: " + customerName + " and id: " + id + " not found.");
        }
        return inquiry;
    }

    @RequestMapping(value = "/{customerName}/inquiries", method = RequestMethod.POST, produces = "application/json")
    public Inquiry create(@PathVariable(value = "customerName") String customerName, @RequestBody Inquiry inquiry) {
        return inquiryService.create(customerName, inquiry);
    }

    @RequestMapping(value = "/{customerName}/inquiries/{id}", method = RequestMethod.PUT, produces = "application/json")
    public Inquiry update(@PathVariable(value = "customerName") String customerName, @PathVariable(value = "id") Long id,
                          @RequestBody Inquiry inquiry) {
        return inquiryService.update(customerName, id, inquiry);
    }

    @RequestMapping(value = "/{customerName}/inquiries/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@PathVariable(value = "customerName") String customerName, @PathVariable(value = "id") Long id) {
        inquiryService.delete(customerName, id);
    }

    @ExceptionHandler(InquiryNotFoundException.class)
    public void handleInquiryNotFound(InquiryNotFoundException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
