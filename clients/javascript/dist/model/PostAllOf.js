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
 * The PostAllOf model module.
 * @module model/PostAllOf
 * @version 0.0.1-SNAPSHOT
 */
var PostAllOf = /*#__PURE__*/function () {
  /**
   * Constructs a new <code>PostAllOf</code>.
   * @alias module:model/PostAllOf
   * @param authorId {String} author id
   * @param created {Date} date-time of post creation
   * @param updated {Date} date-time of last post update
   * @param markdown {String} post markdown
   */
  function PostAllOf(authorId, created, updated, markdown) {
    _classCallCheck(this, PostAllOf);

    PostAllOf.initialize(this, authorId, created, updated, markdown);
  }
  /**
   * Initializes the fields of this object.
   * This method is used by the constructors of any subclasses, in order to implement multiple inheritance (mix-ins).
   * Only for internal use.
   */


  _createClass(PostAllOf, null, [{
    key: "initialize",
    value: function initialize(obj, authorId, created, updated, markdown) {
      obj['authorId'] = authorId;
      obj['created'] = created;
      obj['updated'] = updated;
      obj['markdown'] = markdown;
    }
    /**
     * Constructs a <code>PostAllOf</code> from a plain JavaScript object, optionally creating a new instance.
     * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @param {module:model/PostAllOf} obj Optional instance to populate.
     * @return {module:model/PostAllOf} The populated <code>PostAllOf</code> instance.
     */

  }, {
    key: "constructFromObject",
    value: function constructFromObject(data, obj) {
      if (data) {
        obj = obj || new PostAllOf();

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

  return PostAllOf;
}();
/**
 * author id
 * @member {String} authorId
 */


PostAllOf.prototype['authorId'] = undefined;
/**
 * date-time of post creation
 * @member {Date} created
 */

PostAllOf.prototype['created'] = undefined;
/**
 * date-time of last post update
 * @member {Date} updated
 */

PostAllOf.prototype['updated'] = undefined;
/**
 * post markdown
 * @member {String} markdown
 */

PostAllOf.prototype['markdown'] = undefined;
var _default = PostAllOf;
exports["default"] = _default;