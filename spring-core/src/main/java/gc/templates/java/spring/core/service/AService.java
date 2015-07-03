package gc.templates.java.spring.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author: Gennadii Cherniaiev
 * Date: 7/3/2015
 */
@Component
public class AService {

    @Autowired
    private BService bService;

    @Autowired
    private CService cService;
}
