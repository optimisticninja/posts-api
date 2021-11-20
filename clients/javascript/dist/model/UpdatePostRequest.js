"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _ApiClient = _interopRequireDefault(require("../ApiClient"));

var _PostUpdateMask = _interopRequireDefault(require("./PostUpdateMask"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

/**
 * The UpdatePostRequest model module.
 * @module model/UpdatePostRequest
 * @version 0.0.1-SNAPSHOT
 */
var UpdatePostRequest = /*#__PURE__*/function () {
  /**
   * Constructs a new <code>UpdatePostRequest</code>.
   * update post request
   * @alias module:model/UpdatePostRequest
   * @param updateMask {Array.<module:model/PostUpdateMask>} 
   */
  function UpdatePostRequest(updateMask) {
    _classCallCheck(this, UpdatePostRequest);

    UpdatePostRequest.initialize(this, updateMask);
  }
  /**
   * Initializes the fields of this object.
   * This method is used by the constructors of any subclasses, in order to implement multiple inheritance (mix-ins).
   * Only for internal use.
   */


  _createClass(UpdatePostRequest, null, [{
    key: "initialize",
    value: function initialize(obj, updateMask) {
      obj['updateMask'] = updateMask;
    }
    /**
     * Constructs a <code>UpdatePostRequest</code> from a plain JavaScript object, optionally creating a new instance.
     * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @param {module:model/UpdatePostRequest} obj Optional instance to populate.
     * @return {module:model/UpdatePostRequest} The populated <code>UpdatePostRequest</code> instance.
     */

  }, {
    key: "constructFromObject",
    value: function constructFromObject(data, obj) {
      if (data) {
        obj = obj || new UpdatePostRequest();

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

        if (data.hasOwnProperty('updateMask')) {
          obj['updateMask'] = _ApiClient["default"].convertToType(data['updateMask'], [_PostUpdateMask["default"]]);
        }
      }

      return obj;
    }
  }]);

  return UpdatePostRequest;
}();
/**
 * post title
 * @member {String} title
 */


UpdatePostRequest.prototype['title'] = undefined;
/**
 * post summary
 * @member {String} summary
 */

UpdatePostRequest.prototype['summary'] = undefined;
/**
 * post markdown
 * @member {String} markdown
 */

UpdatePostRequest.prototype['markdown'] = undefined;
/**
 * image url
 * @member {String} imageUrl
 */

UpdatePostRequest.prototype['imageUrl'] = undefined;
/**
 * @member {Array.<module:model/PostUpdateMask>} updateMask
 */

UpdatePostRequest.prototype['updateMask'] = undefined;
var _default = UpdatePostRequest;
exports["default"] = _default;