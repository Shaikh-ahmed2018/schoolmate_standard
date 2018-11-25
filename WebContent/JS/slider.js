
        var tpj = jQuery;
        tpj.noConflict();
        var revapi1;

        tpj(document).ready(function () {


            if (tpj.fn.cssOriginal != undefined)

                tpj.fn.css = tpj.fn.cssOriginal;


           
                revapi1 = tpj('#rev_slider_1_1').show(

                    {


                        delay: 11000,


                        startwidth: 1170,


                        startheight: 400,


                        hideThumbs: 200,





                        thumbWidth: 100,


                        thumbHeight: 50,


                        thumbAmount: 3,





                        navigationType: "none",


                        navigationArrows: "nexttobullets",


                        navigationStyle: "round",





                        touchenabled: "on",


                        onHoverStop: "off",





                        navOffsetHorizontal: 0,


                        navOffsetVertical: 20,





                        shadow: 0,


                        fullWidth: "on",





                        stopLoop: "off",


                        stopAfterLoops: -1,


                        stopAtSlide: -1,





                        shuffle: "off",





                        hideSliderAtLimit: 0,


                        hideCaptionAtLimit: 0,


                        hideAllCaptionAtLilmit: 0


                    });




        }); //ready





