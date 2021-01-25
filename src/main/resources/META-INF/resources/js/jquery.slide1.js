/*!
 * jquery.slide v1.1.0
 * A simple jQuery slider.
 * https://github.com/cobish/jquery.slide

 * Copyright (c) 2016, cobish
 * Released under the MIT license.
 */
(function($, window) {
    'use strict';

    var slide = {
        // default options
        defaults: {
            isAutoSlide: true,                // è‡ªåŠ¨è½®æ’­
            isHoverStop: true,                // é¼ æ ‡ç§»ä¸Šæ˜¯å¦åœæ­¢è½®æ’­
            isBlurStop: true,                 // Windowå¤±åŽ»ç„¦ç‚¹æ˜¯å¦åœæ­¢è½®æ’­
            isShowDots: true,                 // æ˜¯å¦æ˜¾ç¤ºçŠ¶æ€ç‚¹
            isShowArrow: true,                // æ˜¯å¦æ˜¾ç¤ºå·¦å³ç®­å¤´
            isHoverShowArrow: true,           // æ˜¯å¦é¼ æ ‡ç§»ä¸Šæ‰æ˜¾ç¤ºç®­å¤´
            isLoadAllImgs: false,             // æ˜¯å¦ä¸€æ¬¡æ€§åŠ è½½å®Œå…¨éƒ¨å›¾ç‰‡
            slideSpeed: 10000,                // è½®æ’­é€Ÿåº¦ (ms)
            switchSpeed: 500,                 // å›¾ç‰‡åˆ‡æ¢é€Ÿåº¦ (ms)
            dotsClass: 'dots',                // çŠ¶æ€ç‚¹æ ·å¼
            dotActiveClass: 'active',         // çŠ¶æ€ç‚¹æ¿€æ´»æ ·å¼
            dotsEvent: 'click',               // çŠ¶æ€ç‚¹äº‹ä»¶ï¼Œclickæˆ–mouseoveræˆ–mouseenter
            arrowClass: 'arrow',              // ç®­å¤´æ ·å¼
            arrowLeftClass: 'arrow-left',     // å·¦ç®­å¤´æ ·å¼
            arrowRightClass: 'arrow-right'    // å³ç®­å¤´æ ·å¼
        },

        // curr options
        options: null,

        // å½“å‰ç´¢å¼•
        curIndex: 0,

        // å®šæ—¶å™¨
        timer: null,

        // çŠ¶æ€ç‚¹é›†åˆ
        dotsList: [],

        // init function
        init: function(elem, options) {
            var $self = $(elem),
                list = $self.find('ul li'),
                self = this,
                o;

            o = this.options = $.extend({}, this.defaults, options);

            // æ˜¾ç¤ºçŠ¶æ€ç‚¹
            if (o.isShowDots) {
                this._createDots(elem, list);
            }

            // æ˜¾ç¤ºå·¦å³ç®­å¤´
            if (o.isShowArrow) {
                this._createArrow(elem, list);
            }

            // ä¸€æ¬¡æ€§åŠ è½½å®Œå…¨éƒ¨å›¾ç‰‡
            if (o.isLoadAllImgs) {
                list.each(function() {
                    $(this).css({
                        'background': 'url(' + $(this).attr('data-bg') + ')',
                        'opacity': '0',
                        'z-index': '0',
                        'background-position': '50% 50%',
                        'background-size': 'cover'
                    });
                    $(this).attr('data-bg', '');
                });
            }

            // æ˜¾ç¤ºç¬¬ä¸€ä¸ª
            this._showBlock(list[this.curIndex]);

            // è‡ªåŠ¨è½®æ’­
            if (o.isAutoSlide) {
                this._defaultSlide(list);

                // é¼ æ ‡ç§»å…¥ç§»é™¤äº‹ä»¶
                if (o.isHoverStop) {
                    var className = $self.attr('class');
                    $self.on('mouseenter', function(e) {
                        clearInterval(self.timer);
                    }).on('mouseleave', function() {
                        clearInterval(self.timer);
                        self._defaultSlide(list);
                    });
                }

                // WindowèŽ·å–å¤±åŽ»ç„¦ç‚¹äº‹ä»¶
                if (o.isBlurStop) {
                    $(window).on('blur', function() {
                        clearInterval(self.timer);
                    }).on('focus', function() {
                        clearInterval(self.timer);
                        self._defaultSlide(list);
                    });
                }
            }
        },

        // default slide
        _defaultSlide: function(list) {
            var self = this;
            this.timer = setInterval(function() {
                self._hideBlock(list[self.curIndex]);
                self.curIndex =  (self.curIndex + 1) % list.length;
                self._showBlock(list[self.curIndex]);
            }, this.options.slideSpeed);
        },

        // show block
        _showBlock: function(block) {
            var o = this.options,
                $block = $(block),
                bg = $(block).attr('data-bg');

            if (bg) {
                $block.css({
                    'background': 'url(' + bg + ')',
                    'opacity': '0',
                    'z-index': '0',
                    'background-position': '50% 50%',
                    'background-size': 'cover'
                });
                $block.attr('data-bg', '');
            }

            $block.stop().animate({
                'opacity': '1',
                'z-index': '1'
            }, o.switchSpeed);

            if (o.isShowDots) {
                $(this.dotsList[this.curIndex]).addClass(o.dotActiveClass);
            }
        },

        // hide block
        _hideBlock: function(block) {
            var o = this.options;

            $(block).stop().animate({
                'opacity': '0',
                'z-index': '0'
            }, o.switchSpeed);

            if (o.isShowDots) {
                $(this.dotsList[this.curIndex]).removeClass(o.dotActiveClass);
            }
        },

        // create dots
        _createDots: function(elem, list) {
            var self = this,
                dotsEvent = this.options.dotsEvent;

            var dots = $('<ol/>', {
                class: this.options.dotsClass
            });

            var dotsList = [];
            for (var i = 0; i < list.length; i++) {
                dotsList[i] = $('<li/>');
                dots.append(dotsList[i]);
            }

            $(elem).append(dots);
            this.dotsList = dotsList;

            // dotsæ·»åŠ äº‹ä»¶
            if (dotsEvent === 'click' || dotsEvent === 'mouseover' || dotsEvent === 'mouseenter') {
                dots.find('li').on(dotsEvent, function() {
                    self._hideBlock(list[self.curIndex]);
                    self.curIndex =  $(this).index();
                    self._showBlock(list[self.curIndex]);
                });
            }
        },

        // create arrowClass
        _createArrow: function(elem, list) {
            var self = this;

            var arrow = $('<div/>', {
                class: this.options.arrowClass
            });

            var leftArrow = $('<a/>', {
                class: this.options.arrowLeftClass
            });

            var rightArrow = $('<a/>', {
                class: this.options.arrowRightClass
            });

            arrow.append(leftArrow).append(rightArrow);
            $(elem).append(arrow);

            if (this.options.isHoverShowArrow) {
                arrow.css('opacity', 0);
                $(elem).on('mouseenter', function() {
                    arrow.css('opacity', 1);
                }).on('mouseleave', function() {
                    arrow.css('opacity', 0);
                });
            }

            leftArrow.on('click', function() {
                self._hideBlock(list[self.curIndex]);
                self.curIndex = (list.length + (self.curIndex - 1)) % list.length;
                self._showBlock(list[self.curIndex]);
            });

            rightArrow.on('click', function() {
                self._hideBlock(list[self.curIndex]);
                self.curIndex =  (self.curIndex + 1) % list.length;
                self._showBlock(list[self.curIndex]);
            });
        }
    };

    // main function
    $.fn.slide = function(options) {
        slide.init(this, options);
        return this;
    };

})(jQuery, window);