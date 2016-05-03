package org.gradle.jaxb;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Marshall {
	
	public static String marshall(Response response) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		ByteArrayOutputStream arrayOut = new ByteArrayOutputStream();
		jaxbMarshaller.marshal(response, arrayOut);
		jaxbMarshaller.marshal(response, System.out);
		return arrayOut.toString("UTF-8");
	}
	
	public static Response createResponse(BaseData data){
		Response response = new Response();
		response.setCode(888);
		response.setData(data);
		return response;
	}
	
	public static void main(String[] args) throws Exception {
		BaseData data = createData(true);
		Response response = createResponse(data);
		String xml = marshall(response);
		Response uResponse = unmarshall(xml);
		System.out.println("Unmarshall: " + uResponse);

		System.out.println("------------------");
		
		data = createData(false);
		response = createResponse(data);
		xml = marshall(response);
		
		uResponse = unmarshall(xml);
		System.out.println("Unmarshall: " + uResponse);
	}

	private static Response unmarshall(String xml) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Object response = unmarshaller.unmarshal(new StringReader(xml));
		return (Response) response;
	}

	private static BaseData createData(boolean isBinary) {
		if (isBinary) {
			BinaryData data = new BinaryData();
			data.setSize(100);
			return data;
		} else {
			StringData data = new StringData();
			data.setContent("This is response content!");
			return data;
		}
	}
}
