module.exports = function(grunt) {

    // 1. All configuration goes here 
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        concat: {   
            dist: {
                src: [
                    'js/plugins.js', // plugins.js for old browsers
                    'js/mobile-detect.js', // detect mobile
                    'js/mobile-detect-modernizr.js', // hijack modernizr to use detect mobile above
                    'js/iscroll513.js', // iscroll for menu
                    'js/drawer310.js', // drawer burger menu
                    'js/featherlight135.js', // lightbox
                    'js/js.cookie.js', // cookie plugin
                    'js/notify.js', // web notifications wrapper
                    'js/detect-audio-autoplay.js', // check if browser supports audio autoplay
                    'js/snowfall.jquery.js', // SNOWFALL! WHEN VICTORY!
                    'js/main.js' // our custom js

                ],
                dest: 'js/build/production.js',
            }
        },

        uglify: {
            build: {
                src: 'js/build/production.js',
                dest: 'js/production.min.js'
            }
        },

        cssmin: {
          options: {
            keepBreaks: true,
            mediaMerging: false,
            mergeMediaQueries: false,
            removeDuplicateMediaQueries: false,
            aggressiveMerging: false,
            shorthandCompacting: false,
            roundingPrecision: -1
          },
          target: {
            files: {
              'css/build/style.build.min.css': ['css/normalize.css', 'css/main.css', 'css/mq.css', 'css/featherlight135.css', 'css/drawer310.css', 'css/csshake.css', 'css/haxors.css']
            }
          }
        },

        autoprefixer: {
            dist: {
                files: {
                    'css/style.min.css': 'css/build/style.build.min.css'
                }
            }
        },        

        watch: {
            scripts: {
                files: ['js/*.js'],
                tasks: ['concat', 'uglify'],
                options: {
                    spawn: false,
                },
            } 
        }        

    });

    // 3. Where we tell Grunt we plan to use this plug-in.
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-autoprefixer');   
    grunt.loadNpmTasks('grunt-contrib-watch');

    // 4. Where we tell Grunt what to do when we type "grunt" into the terminal.
    grunt.registerTask('default', ['concat', 'uglify', 'cssmin', 'autoprefixer']);    

};

