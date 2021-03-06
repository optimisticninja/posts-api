# @optimisticninja/posts-api-js-client

@OptimisticninjaPostsApiJsClient - JavaScript client for @optimisticninja/posts-api-js-client
Posts API for optimistic.ninja
This SDK is automatically generated by the [OpenAPI Generator](https://openapi-generator.tech) project:

- API version: 0.0.1
- Package version: 0.0.1-SNAPSHOT
- Build package: org.openapitools.codegen.languages.JavascriptClientCodegen

## Installation

### For [Node.js](https://nodejs.org/)

#### npm

To publish the library as a [npm](https://www.npmjs.com/), please follow the procedure in ["Publishing npm packages"](https://docs.npmjs.com/getting-started/publishing-npm-packages).

Then install it via:

```shell
npm install @optimisticninja/posts-api-js-client --save
```

Finally, you need to build the module:

```shell
npm run build
```

##### Local development

To use the library locally without publishing to a remote npm registry, first install the dependencies by changing into the directory containing `package.json` (and this README). Let's call this `JAVASCRIPT_CLIENT_DIR`. Then run:

```shell
npm install
```

Next, [link](https://docs.npmjs.com/cli/link) it globally in npm with the following, also from `JAVASCRIPT_CLIENT_DIR`:

```shell
npm link
```

To use the link you just defined in your project, switch to the directory you want to use your @optimisticninja/posts-api-js-client from, and run:

```shell
npm link /path/to/<JAVASCRIPT_CLIENT_DIR>
```

Finally, you need to build the module:

```shell
npm run build
```

#### git

If the library is hosted at a git repository, e.g.https://github.com/GIT_USER_ID/GIT_REPO_ID
then install it via:

```shell
    npm install GIT_USER_ID/GIT_REPO_ID --save
```

### For browser

The library also works in the browser environment via npm and [browserify](http://browserify.org/). After following
the above steps with Node.js and installing browserify with `npm install -g browserify`,
perform the following (assuming *main.js* is your entry file):

```shell
browserify main.js > bundle.js
```

Then include *bundle.js* in the HTML pages.

### Webpack Configuration

Using Webpack you may encounter the following error: "Module not found: Error:
Cannot resolve module", most certainly you should disable AMD loader. Add/merge
the following section to your webpack config:

```javascript
module: {
  rules: [
    {
      parser: {
        amd: false
      }
    }
  ]
}
```

## Getting Started

Please follow the [installation](#installation) instruction and execute the following JS code:

```javascript
var @OptimisticninjaPostsApiJsClient = require('@optimisticninja/posts-api-js-client');

var defaultClient = @OptimisticninjaPostsApiJsClient.ApiClient.instance;
// Configure OAuth2 access token for authorization: auth0
var auth0 = defaultClient.authentications['auth0'];
auth0.accessToken = "YOUR ACCESS TOKEN"

var api = new @OptimisticninjaPostsApiJsClient.PostsApi()
var createPostRequest = new @OptimisticninjaPostsApiJsClient.CreatePostRequest(); // {CreatePostRequest} 
api.createPost(createPostRequest).then(function() {
  console.log('API called successfully.');
}, function(error) {
  console.error(error);
});


```

## Documentation for API Endpoints

All URIs are relative to *https://localhost:8443/v1*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*@OptimisticninjaPostsApiJsClient.PostsApi* | [**createPost**](docs/PostsApi.md#createPost) | **POST** /posts | create post
*@OptimisticninjaPostsApiJsClient.PostsApi* | [**deletePost**](docs/PostsApi.md#deletePost) | **DELETE** /posts/{postId} | delete post
*@OptimisticninjaPostsApiJsClient.PostsApi* | [**getPost**](docs/PostsApi.md#getPost) | **GET** /posts/{postId} | get post
*@OptimisticninjaPostsApiJsClient.PostsApi* | [**getPosts**](docs/PostsApi.md#getPosts) | **GET** /posts | get posts
*@OptimisticninjaPostsApiJsClient.PostsApi* | [**updatePost**](docs/PostsApi.md#updatePost) | **PATCH** /posts/{postId} | update post


## Documentation for Models

 - [@OptimisticninjaPostsApiJsClient.CreatePostRequest](docs/CreatePostRequest.md)
 - [@OptimisticninjaPostsApiJsClient.ListPostsResponse](docs/ListPostsResponse.md)
 - [@OptimisticninjaPostsApiJsClient.Post](docs/Post.md)
 - [@OptimisticninjaPostsApiJsClient.PostAllOf](docs/PostAllOf.md)
 - [@OptimisticninjaPostsApiJsClient.PostSummary](docs/PostSummary.md)
 - [@OptimisticninjaPostsApiJsClient.PostUpdateMask](docs/PostUpdateMask.md)
 - [@OptimisticninjaPostsApiJsClient.UpdatePostRequest](docs/UpdatePostRequest.md)


## Documentation for Authorization



### auth0


- **Type**: OAuth
- **Flow**: accessCode
- **Authorization URL**: https://dev-q47yvlx5.us.auth0.com/authorize?audience&#x3D;https://localhost:8443/v1
- **Scopes**: 
  - posts:l:w: modify own posts
  - posts:g:w: modify all posts

