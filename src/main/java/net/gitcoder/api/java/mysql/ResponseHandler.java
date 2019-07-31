package net.gitcoder.api.java.mysql;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public interface ResponseHandler<H, R> {
    R handleResponse(H handle) throws Exception;
}
