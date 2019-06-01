'use strict';

let gulp = require('gulp');
let sass = require('gulp-sass');
let wait = require('gulp-wait');
const utils = require('./utils');

gulp.task('sass', function () {
  return gulp.src('../src/assets/sass/**/*.scss')
    .pipe(wait(2000))
    .pipe(sass().on('error', sass.logError))
    .pipe(gulp.dest('../static/css'));
});

gulp.task('sass:watch', function () {
  gulp.watch('../src/assets/sass/**/*.scss', ['sass']);
});
