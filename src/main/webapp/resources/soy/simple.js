// This file was automatically generated from simple.soy.
// Please don't edit this file by hand.

if (typeof examples == 'undefined') { var examples = {}; }
if (typeof examples.simple == 'undefined') { examples.simple = {}; }


examples.simple.helloWorld = function(opt_data, opt_ignored) {
  return 'Hello world!';
};


examples.simple.helloName = function(opt_data, opt_ignored) {
  return (! opt_data.greetingWord) ? 'Hello ' + soy.$$escapeHtml(opt_data.name) + '!' : soy.$$escapeHtml(opt_data.greetingWord) + ' ' + soy.$$escapeHtml(opt_data.name) + '!';
};


examples.simple.helloNames = function(opt_data, opt_ignored) {
  var output = examples.simple.helloName(opt_data) + '<br>';
  var additionalNameList18 = opt_data.additionalNames;
  var additionalNameListLen18 = additionalNameList18.length;
  if (additionalNameListLen18 > 0) {
    for (var additionalNameIndex18 = 0; additionalNameIndex18 < additionalNameListLen18; additionalNameIndex18++) {
      var additionalNameData18 = additionalNameList18[additionalNameIndex18];
      output += examples.simple.helloName({name: additionalNameData18}) + ((! (additionalNameIndex18 == additionalNameListLen18 - 1)) ? '<br>' : '');
    }
  } else {
    output += 'No additional people to greet.';
  }
  return output;
};
