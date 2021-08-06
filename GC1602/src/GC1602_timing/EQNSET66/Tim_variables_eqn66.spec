// EQNSET66 => "EQN_Bridge";
parameter spec Tim_variables_eqn66 {
  // ----- For digInOut variables ------
  // For port @
  var Double numer_eqn66;
  var Double denom_eqn66;
  var Double var_eqn66;
  var Double offset_eqn66;
  var Time per_eqn66 = (numer_eqn66 / denom_eqn66 * var_eqn66) * 1 ns;
}
