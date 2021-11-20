# @OptimisticninjaPostsApiJsClient.PostsApi

All URIs are relative to *https://localhost:8443/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createPost**](PostsApi.md#createPost) | **POST** /posts | create post
[**deletePost**](PostsApi.md#deletePost) | **DELETE** /posts/{postId} | delete post
[**getPost**](PostsApi.md#getPost) | **GET** /posts/{postId} | get post
[**getPosts**](PostsApi.md#getPosts) | **GET** /posts | get posts
[**updatePost**](PostsApi.md#updatePost) | **PATCH** /posts/{postId} | update post



## createPost

> createPost(createPostRequest)

create post

create post

### Example

```javascript
import @OptimisticninjaPostsApiJsClient from '@optimisticninja/posts-api-js-client';
let defaultClient = @OptimisticninjaPostsApiJsClient.ApiClient.instance;
// Configure OAuth2 access token for authorization: auth0
let auth0 = defaultClient.authentications['auth0'];
auth0.accessToken = 'YOUR ACCESS TOKEN';

let apiInstance = new @OptimisticninjaPostsApiJsClient.PostsApi();
let createPostRequest = new @OptimisticninjaPostsApiJsClient.CreatePostRequest(); // CreatePostRequest | 
apiInstance.createPost(createPostRequest).then(() => {
  console.log('API called successfully.');
}, (error) => {
  console.error(error);
});

```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **createPostRequest** | [**CreatePostRequest**](CreatePostRequest.md)|  | 

### Return type

null (empty response body)

### Authorization

[auth0](../README.md#auth0)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: Not defined


## deletePost

> deletePost(postId)

delete post

delete a post

### Example

```javascript
import @OptimisticninjaPostsApiJsClient from '@optimisticninja/posts-api-js-client';
let defaultClient = @OptimisticninjaPostsApiJsClient.ApiClient.instance;
// Configure OAuth2 access token for authorization: auth0
let auth0 = defaultClient.authentications['auth0'];
auth0.accessToken = 'YOUR ACCESS TOKEN';

let apiInstance = new @OptimisticninjaPostsApiJsClient.PostsApi();
let postId = 046b6c7f-0b8a-43b9-b35d-6489e6daee91; // String | post ID
apiInstance.deletePost(postId).then(() => {
  console.log('API called successfully.');
}, (error) => {
  console.error(error);
});

```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **postId** | **String**| post ID | 

### Return type

null (empty response body)

### Authorization

[auth0](../README.md#auth0)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined


## getPost

> Post getPost(postId)

get post

get post

### Example

```javascript
import @OptimisticninjaPostsApiJsClient from '@optimisticninja/posts-api-js-client';

let apiInstance = new @OptimisticninjaPostsApiJsClient.PostsApi();
let postId = 046b6c7f-0b8a-43b9-b35d-6489e6daee91; // String | post ID
apiInstance.getPost(postId).then((data) => {
  console.log('API called successfully. Returned data: ' + data);
}, (error) => {
  console.error(error);
});

```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **postId** | **String**| post ID | 

### Return type

[**Post**](Post.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## getPosts

> ListPostsResponse getPosts(opts)

get posts

get posts

### Example

```javascript
import @OptimisticninjaPostsApiJsClient from '@optimisticninja/posts-api-js-client';

let apiInstance = new @OptimisticninjaPostsApiJsClient.PostsApi();
let opts = {
  'page': 0, // Number | page offset
  'size': 50, // Number | page size
  'query': DD-WRT // String | query
};
apiInstance.getPosts(opts).then((data) => {
  console.log('API called successfully. Returned data: ' + data);
}, (error) => {
  console.error(error);
});

```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **page** | **Number**| page offset | [optional] 
 **size** | **Number**| page size | [optional] 
 **query** | **String**| query | [optional] 

### Return type

[**ListPostsResponse**](ListPostsResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## updatePost

> updatePost(postId, updatePostRequest)

update post

update a pser

### Example

```javascript
import @OptimisticninjaPostsApiJsClient from '@optimisticninja/posts-api-js-client';
let defaultClient = @OptimisticninjaPostsApiJsClient.ApiClient.instance;
// Configure OAuth2 access token for authorization: auth0
let auth0 = defaultClient.authentications['auth0'];
auth0.accessToken = 'YOUR ACCESS TOKEN';

let apiInstance = new @OptimisticninjaPostsApiJsClient.PostsApi();
let postId = 046b6c7f-0b8a-43b9-b35d-6489e6daee91; // String | post ID
let updatePostRequest = new @OptimisticninjaPostsApiJsClient.UpdatePostRequest(); // UpdatePostRequest | 
apiInstance.updatePost(postId, updatePostRequest).then(() => {
  console.log('API called successfully.');
}, (error) => {
  console.error(error);
});

```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **postId** | **String**| post ID | 
 **updatePostRequest** | [**UpdatePostRequest**](UpdatePostRequest.md)|  | 

### Return type

null (empty response body)

### Authorization

[auth0](../README.md#auth0)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: Not defined

