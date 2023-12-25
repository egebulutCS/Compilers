p1_entry:
	move $fp $sp
	sw $ra 0($sp)
	addiu $sp $sp -4
	sw $fp 0($sp)
	addiu $sp $sp -4
	li $a0 100
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
	li $a2 0
	lw $t1 4($sp)
	addiu $sp $sp 4
loopBranch0:
	blt $t1 $a0 exitLabel1
	lw $a0 4($fp)
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a5 50
	lw $t1 4($sp)
	addiu $sp $sp 4
	beq $a0 $t1 thenBranch3
elseBranch2:
	nop
	b exitLabel4
thenBranch3:
	break
exitLabel4:	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 4($fp)
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $a5 1
	lw $t1 4($sp)
	sub $a0 $t1 $a0
	addiu $sp $sp 4	sw $a0 4($fp)
	sw $a0 0($sp)
	addiu $sp $sp -4
	b loopBranch0
exitLabel1:	sw $a0 0($sp)
	addiu $sp $sp -4
	lw $a0 4($fp)
	sw $a0 0($sp)
	addiu $sp $sp -4
	li $v0 10
	syscall