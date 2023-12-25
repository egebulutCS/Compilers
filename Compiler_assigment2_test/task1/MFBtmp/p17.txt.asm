p17_entry:
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
	lw $a0 12($fp)
