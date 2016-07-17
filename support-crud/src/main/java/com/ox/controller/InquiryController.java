package com.ox.controller;

import com.ox.domain.Inquiry;
import com.ox.service.InquiryService;
import com.ox.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class InquiryController {

    private InquiryService inquiryService;
    private TopicService topicService;

    @Autowired
    public InquiryController(InquiryService inquiryService, TopicService topicService) {
        this.inquiryService = inquiryService;
        this.topicService = topicService;
    }

    @RequestMapping("/customers")
    public String list(Model model) {
        model.addAttribute("inquiries", inquiryService.list());
        return "inquiry/list";
    }

    @RequestMapping("/customers/{customerName}/inquiries")
    public String listCustomerName(@PathVariable(value = "customerName") String customerName, Model model) {
        model.addAttribute("inquiries", inquiryService.listCustomerName(customerName));
        return "inquiry/list";
    }

    @RequestMapping("/customers/{customerName}/inquiries/{id}")
    public String read(@PathVariable(value = "customerName") String customerName, @PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("inquiry", inquiryService.read(customerName, id));
        return "inquiry/view";
    }

    @RequestMapping(value = "/customers/inquiries/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("inquiry", new Inquiry());
        model.addAttribute("topics", topicService.list());
        return "inquiry/form";
    }

    @RequestMapping(value = "/customers/inquiries/create", method = RequestMethod.POST)
    public String save(@Valid Inquiry inquiry, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("topics", topicService.list());
            return "inquiry/form";
        } else {
            Inquiry newInquiry = inquiryService.save(inquiry);
            return "redirect:/customers/" + newInquiry.getCustomerName() + "/inquiries/" + newInquiry.getId();
        }

    }

    @RequestMapping("/customers/{customerName}/inquiries/{id}/edit")
    public String edit(@PathVariable(value = "customerName") String customerName, @PathVariable(value = "id") Long id,
                       Model model) {
        model.addAttribute("inquiry", inquiryService.read(customerName, id));
        model.addAttribute("topics", topicService.list());
        return "inquiry/form";
    }

    @RequestMapping("/customers/{customerName}/inquiries/{id}/delete")
    public String delete(@PathVariable(value = "customerName") String customerName, @PathVariable(value = "id") Long id,
                         RedirectAttributes redirectAttrs) {
        inquiryService.delete(customerName, id);
        redirectAttrs.addFlashAttribute("message", "Inquiry was deleted!");
        return "redirect:/customers";
    }

}
