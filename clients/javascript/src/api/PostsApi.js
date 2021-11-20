/**
 * optimistic.ninja posts API
 * Posts API for optimistic.ninja
 *
 * The version of the OpenAPI document: 0.0.1
 * Contact: j@optimistic.ninja
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 *
 */


import ApiClient from "../ApiClient";
import CreatePostRequest from '../model/CreatePostRequest';
import ListPostsResponse from '../model/ListPostsResponse';
import Post from '../model/Post';
import UpdatePostRequest from '../model/UpdatePostRequest';

/**
* Posts service.
* @module api/PostsApi
* @version 0.0.1-SNAPSHOT
*/
export default class PostsApi {

    /**
    * Constructs a new PostsApi. 
    * @alias module:api/PostsApi
    * @class
    * @param {module:ApiClient} [apiClient] Optional API client implementation to use,
    * default to {@link module:ApiClient#instance} if unspecified.
    */
    constructor(apiClient) {
        this.apiClient = apiClient || ApiClient.instance;
    }



    /**
     * create post
     * create post
     * @param {module:model/CreatePostRequest} createPostRequest 
     * @return {Promise} a {@link https://www.promisejs.org/|Promise}, with an object containing HTTP response
     */
    createPostWithHttpInfo(createPostRequest) {
      let postBody = createPostRequest;
      // verify the required parameter 'createPostRequest' is set
      if (createPostRequest === undefined || createPostRequest === null) {
        throw new Error("Missing the required parameter 'createPostRequest' when calling createPost");
      }

      let pathParams = {
      };
      let queryParams = {
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['auth0'];
      let contentTypes = ['application/json'];
      let accepts = [];
      let returnType = null;
      return this.apiClient.callApi(
        '/posts', 'POST',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null
      );
    }

    /**
     * create post
     * create post
     * @param {module:model/CreatePostRequest} createPostRequest 
     * @return {Promise} a {@link https://www.promisejs.org/|Promise}
     */
    createPost(createPostRequest) {
      return this.createPostWithHttpInfo(createPostRequest)
        .then(function(response_and_data) {
          return response_and_data.data;
        });
    }


    /**
     * delete post
     * delete a post
     * @param {String} postId post ID
     * @return {Promise} a {@link https://www.promisejs.org/|Promise}, with an object containing HTTP response
     */
    deletePostWithHttpInfo(postId) {
      let postBody = null;
      // verify the required parameter 'postId' is set
      if (postId === undefined || postId === null) {
        throw new Error("Missing the required parameter 'postId' when calling deletePost");
      }

      let pathParams = {
        'postId': postId
      };
      let queryParams = {
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['auth0'];
      let contentTypes = [];
      let accepts = [];
      let returnType = null;
      return this.apiClient.callApi(
        '/posts/{postId}', 'DELETE',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null
      );
    }

    /**
     * delete post
     * delete a post
     * @param {String} postId post ID
     * @return {Promise} a {@link https://www.promisejs.org/|Promise}
     */
    deletePost(postId) {
      return this.deletePostWithHttpInfo(postId)
        .then(function(response_and_data) {
          return response_and_data.data;
        });
    }


    /**
     * get post
     * get post
     * @param {String} postId post ID
     * @return {Promise} a {@link https://www.promisejs.org/|Promise}, with an object containing data of type {@link module:model/Post} and HTTP response
     */
    getPostWithHttpInfo(postId) {
      let postBody = null;
      // verify the required parameter 'postId' is set
      if (postId === undefined || postId === null) {
        throw new Error("Missing the required parameter 'postId' when calling getPost");
      }

      let pathParams = {
        'postId': postId
      };
      let queryParams = {
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = [];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Post;
      return this.apiClient.callApi(
        '/posts/{postId}', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null
      );
    }

    /**
     * get post
     * get post
     * @param {String} postId post ID
     * @return {Promise} a {@link https://www.promisejs.org/|Promise}, with data of type {@link module:model/Post}
     */
    getPost(postId) {
      return this.getPostWithHttpInfo(postId)
        .then(function(response_and_data) {
          return response_and_data.data;
        });
    }


    /**
     * get posts
     * get posts
     * @param {Object} opts Optional parameters
     * @param {Number} opts.page page offset
     * @param {Number} opts.size page size
     * @param {String} opts.query query
     * @return {Promise} a {@link https://www.promisejs.org/|Promise}, with an object containing data of type {@link module:model/ListPostsResponse} and HTTP response
     */
    getPostsWithHttpInfo(opts) {
      opts = opts || {};
      let postBody = null;

      let pathParams = {
      };
      let queryParams = {
        'page': opts['page'],
        'size': opts['size'],
        'query': opts['query']
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = [];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = ListPostsResponse;
      return this.apiClient.callApi(
        '/posts', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null
      );
    }

    /**
     * get posts
     * get posts
     * @param {Object} opts Optional parameters
     * @param {Number} opts.page page offset
     * @param {Number} opts.size page size
     * @param {String} opts.query query
     * @return {Promise} a {@link https://www.promisejs.org/|Promise}, with data of type {@link module:model/ListPostsResponse}
     */
    getPosts(opts) {
      return this.getPostsWithHttpInfo(opts)
        .then(function(response_and_data) {
          return response_and_data.data;
        });
    }


    /**
     * update post
     * update a pser
     * @param {String} postId post ID
     * @param {module:model/UpdatePostRequest} updatePostRequest 
     * @return {Promise} a {@link https://www.promisejs.org/|Promise}, with an object containing HTTP response
     */
    updatePostWithHttpInfo(postId, updatePostRequest) {
      let postBody = updatePostRequest;
      // verify the required parameter 'postId' is set
      if (postId === undefined || postId === null) {
        throw new Error("Missing the required parameter 'postId' when calling updatePost");
      }
      // verify the required parameter 'updatePostRequest' is set
      if (updatePostRequest === undefined || updatePostRequest === null) {
        throw new Error("Missing the required parameter 'updatePostRequest' when calling updatePost");
      }

      let pathParams = {
        'postId': postId
      };
      let queryParams = {
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['auth0'];
      let contentTypes = ['application/json'];
      let accepts = [];
      let returnType = null;
      return this.apiClient.callApi(
        '/posts/{postId}', 'PATCH',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null
      );
    }

    /**
     * update post
     * update a pser
     * @param {String} postId post ID
     * @param {module:model/UpdatePostRequest} updatePostRequest 
     * @return {Promise} a {@link https://www.promisejs.org/|Promise}
     */
    updatePost(postId, updatePostRequest) {
      return this.updatePostWithHttpInfo(postId, updatePostRequest)
        .then(function(response_and_data) {
          return response_and_data.data;
        });
    }


}
