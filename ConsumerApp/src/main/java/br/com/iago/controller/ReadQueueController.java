package br.com.iago.controller;

import java.io.File;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class ReadQueueController {

	private File downloadFile;
	
	public ReadQueueController() {
		this.downloadFile = new File("/home/iago/Documents/projetos/FIAP/ambientes_componentes_desenvolvimento/avaliação/file/downloadFile.txt");
	}
	
	public Boolean readQueueToFile() {
		
		try {
			
			CamelContext ctx = new DefaultCamelContext();
			ConnectionFactory connFac = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
			
			ctx.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connFac));
			
			ctx.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					from("jms:avaliacao")
						.to("stream:out")
						.to("file:/home/iago/Documents/projetos/FIAP/ambientes_componentes_desenvolvimento/avaliação/file/downloadFile");
				}
			});
			
			ctx.start();
			Thread.sleep(30*1000);
			ctx.stop();
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
		
		return false;
	}
	
	
}
