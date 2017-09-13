package assetManagement;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asset.Assets;
import com.asset.model.Shop;
import com.asset.service.AssestService;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Assets.class})
public class AssestServiceTest
{

	
	@Autowired
	private AssestService service;
	
	
	@Test
	public void test() throws JsonSyntaxException, JsonIOException, ClassNotFoundException, MalformedURLException, SQLException, IOException
	{
		//fail("Not yet implemented");
		Shop shop = new Shop();
		shop.setName("junitTest");
		
		service.saveShop(shop);
		
		assertEquals(shop.getName(),"junitTest" );
	}

}
