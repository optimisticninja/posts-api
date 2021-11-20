"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _ApiClient = _interopRequireDefault(require("../ApiClient"));

var _CreatePostRequest = _interopRequireDefault(require("../model/CreatePostRequest"));

var _ListPostsResponse = _interopRequireDefault(require("../model/ListPostsResponse"));

var _Post = _interopRequireDefault(require("../model/Post"));

var _UpdatePostRequest = _interopRequireDefault(require("../model/UpdatePostRequest"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

/**
* Posts service.
* @module api/PostsApi
* @version 0.0.1-SNAPSHOT
*/
var PostsApi = /*#__PURE__*/function () {
  /**
  * Constructs a new PostsApi. 
  * @alias module:api/PostsApi
  * @class
  * @param {module:ApiClient} [apiClient] Optional API client implementation to use,
  * default to {@link module:ApiClient#instance} if unspecified.
  */
  function PostsApi(apiClient) {
    _classCallCheck(this, PostsApi);

    this.apiClient = apiClient || _ApiClient["default"].instance;
  }
  /**
   * create post
   * create post
   * @param {module:model/CreatePostRequest} createPostRequest 
   * @return {Promise} a {@link https://www.promisejs.org/|Promise}, with an object containing HTTP response
   */


  _createClass(PostsApi, [{
    key: "createPostWithHttpInfo",
    value: function createPostWithHttpInfo(createPostRequest) {
      var postBody = createPostRequest; // verify the required parameter 'createPostRequest' is set

      if (createPostRequest === undefined || createPostRequest === null) {
        throw new Error("Missing the required parameter 'createPostRequest' when calling createPost");
      }

      var pathParams = {};
      var queryParams = {};
      var headerParams = {};
      var formParams = {};
      var authNames = ['auth0'];
      var contentTypes = ['application/json'];
      var accepts = [];
      var returnType = null;
      return this.apiClient.callApi('/posts', 'POST', pathParams, queryParams, headerParams, formParams, postBody, authNames, contentTypes, accepts, returnType, null);
    }
    /**
     * create post
     * create post
     * @param {module:model/CreatePostRequest} createPostRequest 
     * @return {Promise} a {@link https://www.promisejs.org/|Promise}
     */

  }, {
    key: "createPost",
    value: function createPost(createPostRequest) {
      return this.createPostWithHttpInfo(createPostRequest).then(function (response_and_data) {
        return response_and_data.data;
      });
    }
    /**
     * delete post
     * delete a post
     * @param {String} postId post ID
     * @return {Promise} a {@link https://www.promisejs.org/|Promise}, with an object containing HTTP response
     */

  }, {
    key: "deletePostWithHttpInfo",
    value: function deletePostWithHttpInfo(postId) {
      var postBody = null; // verify the required parameter 'postId' is set

      if (postId === undefined || postId === null) {
        throw new Error("Missing the required parameter 'postId' when calling deletePost");
      }

      var pathParams = {
        'postId': postId
      };
      var queryParams = {};
      var headerParams = {};
      var formParams = {};
      var authNames = ['auth0'];
      var contentTypes = [];
      var accepts = [];
      var returnType = null;
      return this.apiClient.callApi('/posts/{postId}', 'DELETE', pathParams, queryParams, headerParams, formParams, postBody, authNames, contentTypes, accepts, returnType, null);
    }
    /**
     * delete post
     * delete a post
     * @param {String} postId post ID
     * @return {Promise} a {@link https://www.promisejs.org/|Promise}
     */

  }, {
    key: "deletePost",
    value: function deletePost(postId) {
      return this.deletePostWithHttpInfo(postId).then(function (response_and_data) {
        return response_and_data.data;
      });
    }
    /**
     * get post
     * get post
     * @param {String} postId post ID
     * @return {Promise} a {@link https://www.promisejs.org/|Promise}, with an object containing data of type {@link module:model/Post} and HTTP response
     */

  }, {
    key: "getPostWithHttpInfo",
    value: function getPostWithHttpInfo(postId) {
      var postBody = null; // verify the required parameter 'postId' is set

      if (postId === undefined || postId === null) {
        throw new Error("Missing the required parameter 'postId' when calling getPost");
      }

      var pathParams = {
        'postId': postId
      };
      var queryParams = {};
      var headerParams = {};
      var formParams = {};
      var authNames = [];
      var contentTypes = [];
      var accepts = ['application/json'];
      var returnType = _Post["default"];
      return this.apiClient.callApi('/posts/{postId}', 'GET', pathParams, queryParams, headerParams, formParams, postBody, authNames, contentTypes, accepts, returnType, null);
    }
    /**
     * get post
     * get post
     * @param {String} postId post ID
     * @return {Promise} a {@link https://www.promisejs.org/|Promise}, with data of type {@link module:model/Post}
     */

  }, {
    key: "getPost",
    value: function getPost(postId) {
      return this.getPostWithHttpInfo(postId).then(function (response_and_data) {
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

  }, {
    key: "getPostsWithHttpInfo",
    value: function getPostsWithHttpInfo(opts) {
      opts = opts || {};
      var postBody = null;
      var pathParams = {};
      var queryParams = {
        'page': opts['page'],
        'size': opts['size'],
        'query': opts['query']
      };
      var headerParams = {};
      var formParams = {};
      var authNames = [];
      var contentTypes = [];
      var accepts = ['application/json'];
      var returnType = _ListPostsResponse["default"];
      return this.apiClient.callApi('/posts', 'GET', pathParams, queryParams, headerParams, formParams, postBody, authNames, contentTypes, accepts, returnType, null);
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

  }, {
    key: "getPosts",
    value: function getPosts(opts) {
      return this.getPostsWithHttpInfo(opts).then(function (response_and_data) {
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

  }, {
    key: "updatePostWithHttpInfo",
    value: function updatePostWithHttpInfo(postId, updatePostRequest) {
      var postBody = updatePostRequest; // verify the required parameter 'postId' is set

      if (postId === undefined || postId === null) {
        throw new Error("Missing the required parameter 'postId' when calling updatePost");
      } // verify the required parameter 'updatePostRequest' is set


      if (updatePostRequest === undefined || updatePostRequest === null) {
        throw new Error("Missing the required parameter 'updatePostRequest' when calling updatePost");
      }

      var pathParams = {
        'postId': postId
      };
      var queryParams = {};
      var headerParams = {};
      var formParams = {};
      var authNames = ['auth0'];
      var contentTypes = ['application/json'];
      var accepts = [];
      var returnType = null;
      return this.apiClient.callApi('/posts/{postId}', 'PATCH', pathParams, queryParams, headerParams, formParams, postBody, authNames, contentTypes, accepts, returnType, null);
    }
    /**
     * update post
     * update a pser
     * @param {String} postId post ID
     * @param {module:model/UpdatePostRequest} updatePostRequest 
     * @return {Promise} a {@link https://www.promisejs.org/|Promise}
     */

  }, {
    key: "updatePost",
    value: function updatePost(postId, updatePostRequest) {
      return this.updatePostWithHttpInfo(postId, updatePostRequest).then(function (response_and_data) {
        return response_and_data.data;
      });
    }
  }]);

  return PostsApi;
}();

exports["default"] = PostsApi;