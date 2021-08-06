// EQNSET95 => "EQN_PLL_IO";
parameter spec Tim_variables_eqn95 {
  // ----- For digInOut variables ------
  // For port @
  var Double numer_eqn95;
  var Double denom_eqn95;
  var Double var_eqn95;
  var Double offset_eqn95;
  var Time per_eqn95 = (numer_eqn95 / denom_eqn95 * var_eqn95) * 1 ns;
}
