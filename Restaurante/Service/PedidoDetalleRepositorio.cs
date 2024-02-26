using Restaurante.Models;

namespace Restaurante.Service
{
    public class PedidoDetalleRepositorio : IPedidoD
    {

        private Conexion conex = new Conexion();
        public void add(TaPedidoDetalle taPedidoDetalle)
        {
            conex.TaPedidoDetalles.Add(taPedidoDetalle);

        }

        public void eleminar(int id)
        {
            conex.TaPedidoDetalles.Remove(ObtenerPorid(id));
        }

        public IEnumerable<TaPedidoDetalle> GetPedidoD()
        {
            return conex.TaPedidoDetalles;
        }

        public void modificar(TaPedidoDetalle taPedidoDetalle)
        {
            var obj = conex.TaPedidoDetalles.Where(tpro => tpro.IdPedidoDetalle == taPedidoDetalle.IdPedidoDetalle)
            .FirstOrDefault();

            obj.IdPedido = taPedidoDetalle.IdPedido;
            obj.IdPlato = taPedidoDetalle.IdPlato;
            obj.IdPedidoNavigation = taPedidoDetalle.IdPedidoNavigation;
            obj.Cantidad = taPedidoDetalle.Cantidad;
            obj.IdPlatoNavigation = taPedidoDetalle.IdPlatoNavigation;

            conex.SaveChanges();
        }

        public TaPedidoDetalle ObtenerPorid(int id)
        {
            return conex.TaPedidoDetalles.Where(tpro => tpro.IdPedidoDetalle == id).FirstOrDefault();
        }
    }
}
