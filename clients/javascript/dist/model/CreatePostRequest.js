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
 * The CreatePostRequest model module.
 * @module model/CreatePostRequest
 * @version 0.0.1-SNAPSHOT
 */
var CreatePostRequest = /*#__PURE__*/function () {
  /**
   * Constructs a new <code>CreatePostRequest</code>.
   * @alias module:model/CreatePostRequest
   * @param title {String} post title
   * @param summary {String} post summary
   * @param markdown {String} post markdown
   * @param imageUrl {String} image url
   */
  function CreatePostRequest(title, summary, markdown, imageUrl) {
    _classCallCheck(this, CreatePostRequest);

    CreatePostRequest.initialize(this, title, summary, markdown, imageUrl);
  }
  /**
   * Initializes the fields of this object.
   * This method is used by the constructors of any subclasses, in order to implement multiple inheritance (mix-ins).
   * Only for internal use.
   */


  _createClass(CreatePostRequest, null, [{
    key: "initialize",
    value: function initialize(obj, title, summary, markdown, imageUrl) {
      obj['title'] = title;
      obj['summary'] = summary;
      obj['markdown'] = markdown;
      obj['imageUrl'] = imageUrl;
    }
    /**
     * Constructs a <code>CreatePostRequest</code> from a plain JavaScript object, optionally creating a new instance.
     * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @param {module:model/CreatePostRequest} obj Optional instance to populate.
     * @return {module:model/CreatePostRequest} The populated <code>CreatePostRequest</code> instance.
     */

  }, {
    key: "constructFromObject",
    value: function constructFromObject(data, obj) {
      if (data) {
        obj = obj || new CreatePostRequest();

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

  return CreatePostRequest;
}();
/**
 * post title
 * @member {String} title
 */


CreatePostRequest.prototype['title'] = undefined;
/**
 * post summary
 * @member {String} summary
 */

CreatePostRequest.prototype['summary'] = undefined;
/**
 * post markdown
 * @member {String} markdown
 */

CreatePostRequest.prototype['markdown'] = undefined;
/**
 * image url
 * @member {String} imageUrl
 */

CreatePostRequest.prototype['imageUrl'] = undefined;
var _default = CreatePostRequest;
exports["default"] = _default;