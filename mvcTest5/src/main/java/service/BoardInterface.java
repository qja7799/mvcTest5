package service;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardInterface {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException;
}
