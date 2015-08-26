module.exports = function (grunt) {
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		//individual plugin configs

		uglify : {
			js: {
				files: {
					'WebContent/scripts/min/app.scripts.min.js' : 
						[ 'WebContent/scripts/customer/Controller/*.js',
						  'WebContent/scripts/customer/service/*.js',
						  'WebContent/scripts/Owner/Controller/*.js',
						  'WebContent/scripts/Owner/service/*.js'],
						  'WebContent/scripts/min/app.bootstrap.min.js' : 
							  [ 'WebContent/bootstrap/js/*.js']
				}
			}
		},

		cssmin: {
			css: {
				files: {
					'WebContent/styles/min/app.styles.min.css':
						['WebContent/bootstrap/css/bootstrap.css',
						 'WebContent/bootstrap/css/bootstrap.theme.css',
						 'WebContent/styles/eatery.css'
						 ]
				}
			}
		},

		autoprefixer: {
			options: {
				browsers: ['last 3 version', 'ie 8', 'ie 9']
			},
			files: {
				src: 'WebContent/styles/min/app.styles.min.css',
				dest: 'WebContent/styles/min/app.styles.min.css'
			}
		},

		watch: {
			css: {
				files: ['WebContent/styles/eatery.css'],
				tasks: ['cssmin', 'autoprefixer']
			}, js: {
				files: ['WebContent/scripts/customer/Controller/*.js',
				        'WebContent/scripts/customer/service/*.js',
				        'WebContent/scripts/Owner/Controller/*.js',
				        'WebContent/scripts/Owner/service/*.js'],
				        tasks: ['uglify']
			}
		}
		
		
	});
	
	
	//load tasks using grunt.loadNpmTasks()
	
	grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-autoprefixer');
    
   	//register custom tasks using grunt.registerTask()
    grunt.registerTask('default', ['cssmin', 'autoprefixer', 'uglify']);
    grunt.registerTask('custom', ['cssmin', 'autoprefixer', 'uglify', 'watch']);

    
};    