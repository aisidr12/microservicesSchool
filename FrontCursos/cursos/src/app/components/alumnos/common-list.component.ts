import { Directive, OnInit, ViewChild } from "@angular/core";
import { MatPaginator, PageEvent } from "@angular/material/paginator";
import { Generic } from "src/app/models/generic";
import { CommonService } from "src/app/services/common.service";
import Swal from "sweetalert2";

@Directive()
export abstract class CommonListComponent<E extends Generic, S extends CommonService<E>> {

    titulo: string;
    lista: E[];
    protected nombreModelo: string

    public totalRegistros = 0;
    public paginaActual = 0;
    public totalPorPagina = 4;
    public pageSizeOptions: number[] = [3, 5, 10, 25, 100];

    @ViewChild(MatPaginator) paginator: MatPaginator;
    constructor(private service: S) {

    }
    ngOnInit(): void {
        this.calcularRango();
    }

    paginar(event: PageEvent): void {
        this.paginaActual = event.pageIndex;
        this.totalPorPagina = event.pageSize;
        this.calcularRango();
    }

    private calcularRango() {
        this.service.listarPaginas(this.paginaActual.toString(), this.totalPorPagina.toString())
            .subscribe(p => {
                this.lista = p.content as E[];
                this.totalRegistros = p.totalElements as number;
                this.paginator._intl.itemsPerPageLabel = 'Registros'
            });
    }

    public eliminar(e: E): void {
        Swal.fire({
            title: 'Cuidado',
            text: `¿Seguro que desea eliminar a  ${e.nombre}`,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí, Eliminar!'
        }).then((result) => {
            if (result.isConfirmed) {
                this.service.eliminar(e.id).subscribe(() => {
                    this.calcularRango();
                    Swal.fire('Eliminando', `${this.nombreModelo} ${e.nombre} eliminado con exito`, 'success');
                });
            }
        })

    }

}