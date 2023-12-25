p33_entry:
	move $fp $sp
	sw $ra 0($sp)
	addiu $sp $sp -4
	sw $fp 0($sp)
	addiu $sp $sp -4
	li $a0 18
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a0 17
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a0 16
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a0 15
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a0 14
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a0 13
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a0 12
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a0 11
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a0 10
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a0 9
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a0 8
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a0 7
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a0 6
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a0 5
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a0 4
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a0 3
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a0 2
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a0 1
	sw $a0 0($sp)
	addiu $sp $sp -4
	jal f_entry
	lw $ra 4($sp)
	addiu $sp $sp 8
	lw $fp 0($sp)
	jr $ra
f_entry:
	lw $a0 4($fp)

	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 8($fp)

	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 12($fp)

	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 16($fp)

	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 20($fp)

	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 24($fp)

	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 28($fp)

	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 32($fp)

	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 36($fp)

	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 40($fp)

	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 44($fp)

	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 48($fp)

	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 52($fp)

	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 56($fp)

	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 60($fp)

	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 64($fp)

	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 68($fp)

	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 72($fp)

	lw $t1 4($sp)
	add $a0 $t1 $a0
	addiu $sp $sp 4
	lw $t1 4($sp)
	add $a0 $t1 $a0
	addiu $sp $sp 4
	lw $t1 4($sp)
	add $a0 $t1 $a0
	addiu $sp $sp 4
	lw $t1 4($sp)
	add $a0 $t1 $a0
	addiu $sp $sp 4
	lw $t1 4($sp)
	add $a0 $t1 $a0
	addiu $sp $sp 4
	lw $t1 4($sp)
	add $a0 $t1 $a0
	addiu $sp $sp 4
	lw $t1 4($sp)
	add $a0 $t1 $a0
	addiu $sp $sp 4
	lw $t1 4($sp)
	add $a0 $t1 $a0
	addiu $sp $sp 4
	lw $t1 4($sp)
	add $a0 $t1 $a0
	addiu $sp $sp 4
	lw $t1 4($sp)
	add $a0 $t1 $a0
	addiu $sp $sp 4
	lw $t1 4($sp)
	add $a0 $t1 $a0
	addiu $sp $sp 4
	lw $t1 4($sp)
	add $a0 $t1 $a0
	addiu $sp $sp 4
	lw $t1 4($sp)
	add $a0 $t1 $a0
	addiu $sp $sp 4
	lw $t1 4($sp)
	add $a0 $t1 $a0
	addiu $sp $sp 4
	lw $t1 4($sp)
	add $a0 $t1 $a0
	addiu $sp $sp 4
	lw $t1 4($sp)
	add $a0 $t1 $a0
	addiu $sp $sp 4
	lw $t1 4($sp)
	add $a0 $t1 $a0
	addiu $sp $sp 4