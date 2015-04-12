package be.urpi.software.site.core.persistence;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:generator-test-ctx.xml"})
public class TestingUUIDGeneratorTest {
    @Resource
    private TestingRepository testingRepository;

    @Test
    public void testRepository(){
        final Testing testing = new Testing();
        testingRepository.doIets(testing);
        Assert.assertNotNull(testing.getId());
        System.out.println(testing.getId().toString());
    }
}
