package org.ictact.webproceedings.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller used to showcase what happens when an exception is thrown
 *
 */
@Controller
public class CrashController {

    @RequestMapping(value = "/oups", method = RequestMethod.GET)
    public String triggerException() {
        throw new RuntimeException("Expected: controller used to showcase what " +
                "happens when an exception is thrown");
    }


}
