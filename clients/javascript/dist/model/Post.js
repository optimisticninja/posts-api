"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _ApiClient = _interopRequireDefault(require("../ApiClient"));

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
   */
  function Post() {
    _classCallCheck(this, Post);

    Post.initialize(this);
  }
  /**
   * Initializes the fields of this object.
   * This method is used by the constructors of any subclasses, in order to implement multiple inheritance (mix-ins).
   * Only for internal use.
   */


  _createClass(Post, null, [{
    key: "initialize",
    value: function initialize(obj) {}
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

        if (data.hasOwnProperty('id')) {
          obj['id'] = _ApiClient["default"].convertToType(data['id'], 'String');
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

        if (data.hasOwnProperty('title')) {
          obj['title'] = _ApiClient["default"].convertToType(data['title'], 'String');
        }

        if (data.hasOwnProperty('summary')) {
          obj['summary'] = _ApiClient["default"].convertToType(data['summary'], 'String');
        }

        if (data.hasOwnProperty('markdown')) {
          obj['markdown'] = _ApiClient["default"].convertToType(data['markdown'], 'String');
        }

        if (data.hasOwnProperty('imageUrl')) {
          obj['imageUrl'] = _ApiClient["default"].convertToType(data['imageUrl'], 'String');
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
 * post markdown
 * @member {String} markdown
 */

Post.prototype['markdown'] = undefined;
/**
 * image url
 * @member {String} imageUrl
 */

Post.prototype['imageUrl'] = undefined;
var _default = Post;
exports["default"] = _default;