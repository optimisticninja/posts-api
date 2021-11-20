"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _ApiClient = _interopRequireDefault(require("../ApiClient"));

var _PostAllOf = _interopRequireDefault(require("./PostAllOf"));

var _PostSummary = _interopRequireDefault(require("./PostSummary"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

/**
 * The Post model module.
 * @module model/Post
 * @version 0.0.1-SNAPSHOT
 */
var Post = /*#__PURE__*/function () {
  /**
   * Constructs a new <code>Post</code>.
   * @alias module:model/Post
   * @implements module:model/PostSummary
   * @implements module:model/PostAllOf
   * @param authorId {String} author id
   * @param created {Date} date-time of post creation
   * @param updated {Date} date-time of last post update
   * @param markdown {String} post markdown
   */
  function Post(authorId, created, updated, markdown) {
    _classCallCheck(this, Post);

    _PostSummary["default"].initialize(this);

    _PostAllOf["default"].initialize(this, authorId, created, updated, markdown);

    Post.initialize(this, authorId, created, updated, markdown);
  }
  /**
   * Initializes the fields of this object.
   * This method is used by the constructors of any subclasses, in order to implement multiple inheritance (mix-ins).
   * Only for internal use.
   */


  _createClass(Post, null, [{
    key: "initialize",
    value: function initialize(obj, authorId, created, updated, markdown) {
      obj['authorId'] = authorId;
      obj['created'] = created;
      obj['updated'] = updated;
      obj['markdown'] = markdown;
    }
    /**
     * Constructs a <code>Post</code> from a plain JavaScript object, optionally creating a new instance.
     * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @param {module:model/Post} obj Optional instance to populate.
     * @return {module:model/Post} The populated <code>Post</code> instance.
     */

  }, {
    key: "constructFromObject",
    value: function constructFromObject(data, obj) {
      if (data) {
        obj = obj || new Post();

        _PostSummary["default"].constructFromObject(data, obj);

        _PostAllOf["default"].constructFromObject(data, obj);

        if (data.hasOwnProperty('id')) {
          obj['id'] = _ApiClient["default"].convertToType(data['id'], 'String');
        }

        if (data.hasOwnProperty('title')) {
          obj['title'] = _ApiClient["default"].convertToType(data['title'], 'String');
        }

        if (data.hasOwnProperty('summary')) {
          obj['summary'] = _ApiClient["default"].convertToType(data['summary'], 'String');
        }

        if (data.hasOwnProperty('imageUrl')) {
          obj['imageUrl'] = _ApiClient["default"].convertToType(data['imageUrl'], 'String');
        }

        if (data.hasOwnProperty('authorId')) {
          obj['authorId'] = _ApiClient["default"].convertToType(data['authorId'], 'String');
        }

        if (data.hasOwnProperty('created')) {
          obj['created'] = _ApiClient["default"].convertToType(data['created'], 'Date');
        }

        if (data.hasOwnProperty('updated')) {
          obj['updated'] = _ApiClient["default"].convertToType(data['updated'], 'Date');
        }

        if (data.hasOwnProperty('markdown')) {
          obj['markdown'] = _ApiClient["default"].convertToType(data['markdown'], 'String');
        }
      }

      return obj;
    }
  }]);

  return Post;
}();
/**
 * post id
 * @member {String} id
 */


Post.prototype['id'] = undefined;
/**
 * post title
 * @member {String} title
 */

Post.prototype['title'] = undefined;
/**
 * post summary
 * @member {String} summary
 */

Post.prototype['summary'] = undefined;
/**
 * image url
 * @member {String} imageUrl
 */

Post.prototype['imageUrl'] = undefined;
/**
 * author id
 * @member {String} authorId
 */

Post.prototype['authorId'] = undefined;
/**
 * date-time of post creation
 * @member {Date} created
 */

Post.prototype['created'] = undefined;
/**
 * date-time of last post update
 * @member {Date} updated
 */

Post.prototype['updated'] = undefined;
/**
 * post markdown
 * @member {String} markdown
 */

Post.prototype['markdown'] = undefined; // Implement PostSummary interface:

/**
 * post id
 * @member {String} id
 */

_PostSummary["default"].prototype['id'] = undefined;
/**
 * post title
 * @member {String} title
 */

_PostSummary["default"].prototype['title'] = undefined;
/**
 * post summary
 * @member {String} summary
 */

_PostSummary["default"].prototype['summary'] = undefined;
/**
 * image url
 * @member {String} imageUrl
 */

_PostSummary["default"].prototype['imageUrl'] = undefined; // Implement PostAllOf interface:

/**
 * author id
 * @member {String} authorId
 */

_PostAllOf["default"].prototype['authorId'] = undefined;
/**
 * date-time of post creation
 * @member {Date} created
 */

_PostAllOf["default"].prototype['created'] = undefined;
/**
 * date-time of last post update
 * @member {Date} updated
 */

_PostAllOf["default"].prototype['updated'] = undefined;
/**
 * post markdown
 * @member {String} markdown
 */

_PostAllOf["default"].prototype['markdown'] = undefined;
var _default = Post;
exports["default"] = _default;