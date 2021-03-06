"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _ApiClient = _interopRequireDefault(require("../ApiClient"));

var _PostSummary = _interopRequireDefault(require("./PostSummary"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

/**
 * The ListPostsResponse model module.
 * @module model/ListPostsResponse
 * @version 0.0.1-SNAPSHOT
 */
var ListPostsResponse = /*#__PURE__*/function () {
  /**
   * Constructs a new <code>ListPostsResponse</code>.
   * @alias module:model/ListPostsResponse
   * @param postSummaries {Array.<module:model/PostSummary>} list of post summaries
   * @param pageCount {Number} number of pages
   */
  function ListPostsResponse(postSummaries, pageCount) {
    _classCallCheck(this, ListPostsResponse);

    ListPostsResponse.initialize(this, postSummaries, pageCount);
  }
  /**
   * Initializes the fields of this object.
   * This method is used by the constructors of any subclasses, in order to implement multiple inheritance (mix-ins).
   * Only for internal use.
   */


  _createClass(ListPostsResponse, null, [{
    key: "initialize",
    value: function initialize(obj, postSummaries, pageCount) {
      obj['postSummaries'] = postSummaries;
      obj['pageCount'] = pageCount;
    }
    /**
     * Constructs a <code>ListPostsResponse</code> from a plain JavaScript object, optionally creating a new instance.
     * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @param {module:model/ListPostsResponse} obj Optional instance to populate.
     * @return {module:model/ListPostsResponse} The populated <code>ListPostsResponse</code> instance.
     */

  }, {
    key: "constructFromObject",
    value: function constructFromObject(data, obj) {
      if (data) {
        obj = obj || new ListPostsResponse();

        if (data.hasOwnProperty('postSummaries')) {
          obj['postSummaries'] = _ApiClient["default"].convertToType(data['postSummaries'], [_PostSummary["default"]]);
        }

        if (data.hasOwnProperty('nextPage')) {
          obj['nextPage'] = _ApiClient["default"].convertToType(data['nextPage'], 'Number');
        }

        if (data.hasOwnProperty('pageCount')) {
          obj['pageCount'] = _ApiClient["default"].convertToType(data['pageCount'], 'Number');
        }
      }

      return obj;
    }
  }]);

  return ListPostsResponse;
}();
/**
 * list of post summaries
 * @member {Array.<module:model/PostSummary>} postSummaries
 */


ListPostsResponse.prototype['postSummaries'] = undefined;
/**
 * next page
 * @member {Number} nextPage
 */

ListPostsResponse.prototype['nextPage'] = undefined;
/**
 * number of pages
 * @member {Number} pageCount
 */

ListPostsResponse.prototype['pageCount'] = undefined;
var _default = ListPostsResponse;
exports["default"] = _default;